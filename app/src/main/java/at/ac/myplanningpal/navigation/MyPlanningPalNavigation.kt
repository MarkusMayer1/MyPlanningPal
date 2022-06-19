package at.ac.myplanningpal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.myplanningpal.db.MyPlanningPalDB
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.repositories.AppointmentRepository
import at.ac.myplanningpal.repositories.NoteRepository
import at.ac.myplanningpal.screens.*
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.viewmodel.AppointmentViewModelFactory
import at.ac.myplanningpal.viewmodel.NoteViewModel
import at.ac.myplanningpal.viewmodel.NoteViewModelFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun MyPlanningPalNavigation(
    navController: NavHostController = rememberNavController(),
    appointmentViewModel: AppointmentViewModel = viewModel(),
    noteViewModel: NoteViewModel = viewModel()
) {
    NavHost(navController = navController, startDestination = MyPlanningPalScreens.HomeScreen.name) {
        composable(route = MyPlanningPalScreens.HomeScreen.name) { HomeScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarViewScreen.name) { CalendarViewScreen(appointmentViewModel = appointmentViewModel, navController = navController ) }
        composable(route = MyPlanningPalScreens.NoteScreen.name) { NoteScreen(noteViewModel = noteViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.CalendarListViewScreen.name) { CalendarListViewScreen(appointmentViewModel = appointmentViewModel, navController = navController) }
        composable(route = MyPlanningPalScreens.AppointmentSettingsScreen.name) { AppointmentScreen() }
        composable(
            route = MyPlanningPalScreens.AddAppointmentScreen.name + "?appointment={appointment}",
            arguments = listOf(navArgument("appointment") { defaultValue = "" })
        ) { backStackEntry ->
            val appointmentJson = backStackEntry.arguments?.getString("appointment")
            var appointment: Appointment? = null
            if (!appointmentJson.isNullOrEmpty()) {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapter = moshi.adapter(Appointment::class.java).lenient()
                appointment = jsonAdapter.fromJson(appointmentJson)
            }

            AddAppointmentScreen(
                appointmentViewModel = appointmentViewModel,
                navController = navController,
                appointment = appointment
            )
        }
        composable(
            route = MyPlanningPalScreens.AddNoteScreen.name + "?note={note}",
            arguments = listOf(navArgument("note") { defaultValue = "" })
        ) { backStackEntry ->
            val noteJson = backStackEntry.arguments?.getString("note")
            var note: Note? = null
            if (!noteJson.isNullOrEmpty()) {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapter = moshi.adapter(Note::class.java).lenient()
                note = jsonAdapter.fromJson(noteJson)
            }

            AddNoteScreen(
                noteViewModel = noteViewModel,
                navController = navController,
                note =  note
            )
        }
        composable(route = MyPlanningPalScreens.DrawingScreen.name){ DrawingScreen() }
    }
}