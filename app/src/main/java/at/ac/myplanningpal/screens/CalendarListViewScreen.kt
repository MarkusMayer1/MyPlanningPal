package at.ac.myplanningpal.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.widgets.AppointmentWithMonthAndDay
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CalendarListViewScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route =  MyPlanningPalScreens.AddAppointmentScreen.name) }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentCalendarListVewScreen(appointmentViewModel = appointmentViewModel, navController = navController)
    }
}

@Composable
fun MainContentCalendarListVewScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController = rememberNavController()) {
    val appointments: List<Appointment> by appointmentViewModel.appointments.collectAsState()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(Appointment::class.java).lenient()

    LazyColumn {
        items(items = appointmentViewModel.getDates()) { date ->
            AppointmentWithMonthAndDay(
                stringDate = date.toString(),
                appointments = appointments,
                onItemEditClick = { editAppointment ->
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