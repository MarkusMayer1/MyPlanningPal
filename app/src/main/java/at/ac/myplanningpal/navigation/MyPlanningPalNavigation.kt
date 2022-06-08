package at.ac.myplanningpal.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.screens.*
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.viewmodel.NoteViewModel

@Composable
fun MyPlanningPalNavigation(navController: NavHostController = rememberNavController()) {
    val appointmentViewModel: AppointmentViewModel = viewModel()
    val noteViewModel: NoteViewModel = viewModel()

    NavHost(navController = navController, startDestination = MyPlanningPalScreens.HomeScreen.name) {
        composable(route = MyPlanningPalScreens.HomeScreen.name) { HomeScreen(appointmentViewModel = appointmentViewModel) }
        composable(route = MyPlanningPalScreens.CalendarViewScreen.name) { CalendarViewScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.NoteScreen.name) { NoteScreen(noteViewModel = noteViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarListViewScreen.name) { CalendarListViewScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.AppointmentSettingsScreen.name) { AppointmentScreen() }
        composable(route = MyPlanningPalScreens.AddAppointmentScreen.name) { AddAppointmentScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.AddNoteScreen.name) { AddNoteScreen(noteViewModel = noteViewModel, navController = navController) }
    }
}