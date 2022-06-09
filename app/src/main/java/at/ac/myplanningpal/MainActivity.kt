package at.ac.myplanningpal

import android.content.Context
import android.os.Bundle
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.navigation.MyPlanningPalNavigation
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.ui.theme.MyPlanningPalTheme
import at.ac.myplanningpal.viewmodel.ThemeViewModel

val Context.dataStore by preferencesDataStore("settings")

class MainActivity : ComponentActivity() {
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
                            backgroundColor = MaterialTheme.colors.primary,
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
                                icon = { Icon(imageVector = Icons.Default.List, contentDescription = "CalendarListViewScreen") },
                                selected = currentRoute == MyPlanningPalScreens.CalendarListViewScreen.name,
                                onClick = {
                                    navController.navigate(route = MyPlanningPalScreens.CalendarListViewScreen.name)
                                }
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "NoteScreen") },
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
                        MyPlanningPalNavigation(navController = navController)
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