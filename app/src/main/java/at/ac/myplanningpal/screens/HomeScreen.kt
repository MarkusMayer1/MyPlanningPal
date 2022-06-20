package at.ac.myplanningpal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.widgets.TodaysAppointmentRow
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun HomeScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController) {
    MainContentHomeScreen(appointmentViewModel = appointmentViewModel, navController = navController)
}

@Composable
fun MainContentHomeScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Today's Appointments: ", style = MaterialTheme.typography.h5)

        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        val appointments: List<Appointment> by appointmentViewModel.appointments.collectAsState()
        val sortedAppointments = appointments.sortedWith(compareBy({ it.date }, { it.time }))

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Appointment::class.java).lenient()

        LazyColumn {
            items(items = sortedAppointments) { appointment ->
                TodaysAppointmentRow(
                    appointment = appointment,
                    onItemEditClick = {editAppointment ->
                        val appointmentJson = jsonAdapter.toJson(editAppointment)
                        navController.navigate(route = MyPlanningPalScreens.AddAppointmentScreen.name + "?appointment=$appointmentJson")
                    },
                    onItemDeleteClick = { deleteAppointment ->
                        appointmentViewModel.removeAppointment(deleteAppointment)
                    }
                )
            }
        }
    }
}