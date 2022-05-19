package at.ac.myplanningpal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.screens.*

@Composable
fun MyPlanningPalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MyPlanningPalScreens.HomeScreen.name) {
        composable(route = MyPlanningPalScreens.HomeScreen.name) { HomeScreen(navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarViewScreen.name) { CalendarViewScreen( navController = navController) }
        composable(route = MyPlanningPalScreens.NoteScreen.name) { NoteScreen( navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarListViewScreen.name) { CalendarListViewScreen( navController = navController) }
        composable(route = MyPlanningPalScreens.AppointmentSettingsScreen.name) { AppointmentScreen( navController = navController )}
    }
}