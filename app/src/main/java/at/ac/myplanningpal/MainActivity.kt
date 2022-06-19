package at.ac.myplanningpal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.db.MyPlanningPalDB
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.navigation.MyPlanningPalNavigation
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.notifications.createNotificationChannel
import at.ac.myplanningpal.notifications.simpleNotification
import at.ac.myplanningpal.repositories.AppointmentRepository
import at.ac.myplanningpal.repositories.NoteRepository
import at.ac.myplanningpal.ui.theme.MyPlanningPalTheme
import at.ac.myplanningpal.viewmodel.*
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val Context.dataStore by preferencesDataStore("settings")

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var showMenu by remember { mutableStateOf(false) }


            val context = LocalContext.current

            val viewModel = remember {
                ThemeViewModel(context.dataStore)
            }
            val value = viewModel.state.observeAsState().value
            val systemInDarkTheme = isSystemInDarkTheme()

            val lightModeChecked by remember(value) {
                val checked = when (value) {
                    null -> !systemInDarkTheme
                    else -> !value
                }
                mutableStateOf(checked)
            }
            val darkModeChecked by remember(value) {
                val checked = when (value) {
                    null -> systemInDarkTheme
                    else -> value
                }
                mutableStateOf(checked)
            }
            val useDeviceModeChecked by remember(value) {
                val checked = when (value) {
                    null -> true
                    else -> false
                }
                mutableStateOf(checked)
            }

            LaunchedEffect(viewModel) {
                viewModel.request()
            }

            val channelId = "MyPlanningPal"
            //val notificationId = 0

            LaunchedEffect(Unit) {
                createNotificationChannel(channelId, context)
            }

            val db = MyPlanningPalDB.getDatabase(context = context)
            val noteRepository = NoteRepository(dao = db.notesDao())
            val noteViewModel: NoteViewModel = viewModel(
                factory = NoteViewModelFactory(repository = noteRepository)
            )

            val appointmentRepository = AppointmentRepository(dao = db.appointmentsDao())
            val appointmentViewModel: AppointmentViewModel = viewModel(
                factory = AppointmentViewModelFactory(repository = appointmentRepository)
            )

            GlobalScope.launch {
                alarm(context = context, channelId = "MyPlanningPal", notificationId = 0, appointmentViewModel = appointmentViewModel)
            }

            MyPlanningPalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text(text = "MyPlanningPal") },
                            actions = {
                                IconButton(onClick = { showMenu = !showMenu }) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "more"
                                    )
                                }

                                DropdownMenu(
                                    expanded = showMenu,
                                    onDismissRequest = { showMenu = false }) {
                                    Text(
                                        modifier = Modifier.padding(start = 10.dp),
                                        text = "Theme:",
                                        style = MaterialTheme.typography.h6
                                    )

                                    Column(
                                        modifier = Modifier.width(150.dp),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Row {
                                            Checkbox(
                                                modifier = Modifier.padding(end = 5.dp),
                                                checked = if (useDeviceModeChecked) false else lightModeChecked,
                                                onCheckedChange = { viewModel.switchToUseDarkMode(!it) }
                                            )
                                            Text(text = "Light", modifier = Modifier.width(60.dp))
                                        }
                                        Row {
                                            Checkbox(
                                                modifier = Modifier.padding(end = 5.dp),
                                                checked = if (useDeviceModeChecked) false else darkModeChecked,
                                                onCheckedChange = { viewModel.switchToUseDarkMode(it) }
                                            )
                                            Text(text = "Dark", modifier = Modifier.width(60.dp))
                                        }
                                        Row {
                                            Checkbox(
                                                modifier = Modifier.padding(end = 5.dp),
                                                checked = useDeviceModeChecked,
                                                onCheckedChange = {viewModel.switchToUseSystemSettings(it)}
                                            )
                                            Text(text = "System", modifier = Modifier.width(60.dp))
                                        }
                                    }
                                }
                            })
                    },
                    bottomBar = {
                        BottomNavigation(
                            contentColor = Color.White
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "HomeScreen") },
                                selected = currentRoute == MyPlanningPalScreens.HomeScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.HomeScreen.name)
                                }
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "CalendarViewScreen") },
                                selected = currentRoute == MyPlanningPalScreens.CalendarViewScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.CalendarViewScreen.name)
                                }
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.ViewList, contentDescription = "CalendarListViewScreen") },
                                selected = currentRoute == MyPlanningPalScreens.CalendarListViewScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.CalendarListViewScreen.name)
                                }
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Description, contentDescription = "NoteScreen") },
                                selected = currentRoute == MyPlanningPalScreens.NoteScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.NoteScreen.name)
                                }
                            )

                            /*BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "AppointmentSettingsScreen") },
                                selected = currentRoute == MyPlanningPalScreens.AppointmentSettingsScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.AppointmentSettingsScreen.name)
                                }
                            )*/
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MyPlanningPalNavigation(
                            navController = navController,
                            appointmentViewModel = appointmentViewModel,
                            noteViewModel = noteViewModel)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyPlanningPalTheme {
        MyPlanningPalNavigation()
    }
}

suspend fun alarm(context: Context, channelId: String, notificationId: Int, appointmentViewModel: AppointmentViewModel) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    while (true) {
        Log.d("Alarm", "wait 1 min")
        delay(60000L)
        val current = LocalDateTime.now()
        val formatted = current.format(formatter)

        for (appointment in appointmentViewModel.appointments.value) {
            if (appointment.alarm && appointment.date + " " + appointment.time == formatted) {
                simpleNotification(
                    context,
                    channelId,
                    notificationId,
                    appointment.title,
                    appointment.eventDescription ?: ""
                )
            }
        }
        Log.d("Alarm", "notifications sent")
    }
}