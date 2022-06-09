package at.ac.myplanningpal.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.db.MyPlanningPalDB
import at.ac.myplanningpal.repositories.AppointmentRepository
import at.ac.myplanningpal.repositories.NoteRepository
import at.ac.myplanningpal.screens.*
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.viewmodel.AppointmentViewModelFactory
import at.ac.myplanningpal.viewmodel.NoteViewModel
import at.ac.myplanningpal.viewmodel.NoteViewModelFactory

@Composable
fun MyPlanningPalNavigation(navController: NavHostController = rememberNavController()) {
    //
    val context = LocalContext.current
    val db = MyPlanningPalDB.getDatabase(context = context)
    val noteRepository = NoteRepository(dao = db.notesDao())
    val noteViewModel: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(repository = noteRepository)
    )

    val appointmentRepository = AppointmentRepository(dao = db.appointmentsDao())
    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = AppointmentViewModelFactory(repository = appointmentRepository)
    )

    NavHost(navController = navController, startDestination = MyPlanningPalScreens.HomeScreen.name) {
        composable(route = MyPlanningPalScreens.HomeScreen.name) { HomeScreen(appointmentViewModel = appointmentViewModel) }
        composable(route = MyPlanningPalScreens.CalendarViewScreen.name) { CalendarViewScreen(appointmentViewModel = appointmentViewModel, navController = navController ) }
        composable(route = MyPlanningPalScreens.NoteScreen.name) { NoteScreen(noteViewModel = noteViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarListViewScreen.name) { CalendarListViewScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.AppointmentSettingsScreen.name) { AppointmentScreen() }
        composable(route = MyPlanningPalScreens.AddAppointmentScreen.name) { AddAppointmentScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.AddNoteScreen.name) { AddNoteScreen(noteViewModel = noteViewModel, navController = navController) }
    }
}