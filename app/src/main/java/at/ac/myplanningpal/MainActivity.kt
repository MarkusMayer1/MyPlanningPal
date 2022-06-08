package at.ac.myplanningpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.navigation.MyPlanningPalNavigation
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.ui.theme.MyPlanningPalTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MyPlanningPalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text(text = "MyPlanningPal") })
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
                                    navController.navigate(route =  MyPlanningPalScreens.CalendarListViewScreen.name)
                                }
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "NoteScreen") },
                                selected = currentRoute == MyPlanningPalScreens.NoteScreen.name,
                                onClick = {
                                    navController.navigate(route =  MyPlanningPalScreens.NoteScreen.name)
                                }
                            )

                            /*BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "AppointmentSettingsScreen") },
                                selected = currentRoute == MyPlanningPalScreens.AppointmentSettingsScreen.name,
                                onClick = {
                                    navController.navigate(route =  MyPlanningPalScreens.AppointmentSettingsScreen.name)
                                }
                            )*/
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MyPlanningPalNavigation(navController)
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