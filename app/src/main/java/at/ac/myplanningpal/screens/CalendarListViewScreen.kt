package at.ac.myplanningpal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.widgets.AppointmentWithMonthAndDay
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Appointments: ", style = MaterialTheme.typography.h5)

        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        val appointments: List<Appointment> by appointmentViewModel.appointments.collectAsState()
        val sortedAppointments = appointments.sortedWith(compareBy({ it.date }, { it.time }))
        var dates = mutableListOf<LocalDate>()
        for (appointment in appointments) {
            if (!dates.toString().contains(appointment.date)) {
                dates.add(LocalDate.parse(appointment.date, DateTimeFormatter.ISO_LOCAL_DATE))
            }
        }
        dates = dates.distinct().toMutableList()
        dates.sort()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Appointment::class.java).lenient()

        LazyColumn(contentPadding = PaddingValues(bottom = 85.dp)) {
            items(items = dates) { date ->
                AppointmentWithMonthAndDay(
                    date = date,
                    appointments = sortedAppointments,
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
}