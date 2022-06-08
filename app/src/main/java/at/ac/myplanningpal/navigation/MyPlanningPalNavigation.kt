package at.ac.myplanningpal.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.screens.*
import at.ac.myplanningpal.viewmodel.AppointmentViewModel

@Composable
fun MyPlanningPalNavigation(navController: NavHostController = rememberNavController()) {
    val appointmentViewModel: AppointmentViewModel = viewModel()

    NavHost(navController = navController, startDestination = MyPlanningPalScreens.HomeScreen.name) {
        composable(route = MyPlanningPalScreens.HomeScreen.name) { HomeScreen(appointmentViewModel = appointmentViewModel) }
        composable(route = MyPlanningPalScreens.CalendarViewScreen.name) { CalendarViewScreen(appointmentViewModel = appointmentViewModel ) }
        composable(route = MyPlanningPalScreens.NoteScreen.name) { NoteScreen() }
        composable(route = MyPlanningPalScreens.CalendarListViewScreen.name) { CalendarListViewScreen(appointmentViewModel = appointmentViewModel) }
        composable(route = MyPlanningPalScreens.AppointmentSettingsScreen.name) { AppointmentScreen() }
        composable(route = MyPlanningPalScreens.AddAppointmentScreen.name) { AddAppointmentScreen() }
    }
}