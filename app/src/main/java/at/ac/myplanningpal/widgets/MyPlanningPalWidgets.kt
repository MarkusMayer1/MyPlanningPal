package at.ac.myplanningpal.widgets

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.navigation.MyPlanningPalScreens

@Composable
fun BottomBarNavigation(navController: NavController = rememberNavController()) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "HomeScreen") },
            selected = true,
            onClick = {
                navController.navigate(route = MyPlanningPalScreens.HomeScreen.name)
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "CalendarViewScreen") },
            selected = true,
            onClick = {
                navController.navigate(route = MyPlanningPalScreens.CalendarViewScreen.name)
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = "CalendarListViewScreen") },
            selected = true,
            onClick = {
                navController.navigate(route =  MyPlanningPalScreens.CalendarListViewScreen.name)
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "NoteScreen") },
            selected = true,
            onClick = {
                navController.navigate(route =  MyPlanningPalScreens.NoteScreen.name)
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "AppointmentSettingsScreen") },
            selected = true,
            onClick = {
                navController.navigate(route =  MyPlanningPalScreens.AppointmentSettingsScreen.name)
            }
        )
    }
}
