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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.widgets.AppointmentWithMonthAndDay

@Composable
fun CalendarListViewScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentCalendarListVewScreen(appointmentViewModel = appointmentViewModel)
    }
}

@Composable
fun MainContentCalendarListVewScreen(appointmentViewModel: AppointmentViewModel = viewModel()) {
    LazyColumn {
        items(items = appointmentViewModel.getDates()) { date ->
            AppointmentWithMonthAndDay(
                date = date,
                appointments = appointmentViewModel.getAppointments(),
                onItemClick = { appointment ->
                    appointmentViewModel.removeAppointment(appointment)
                }
            )
        }
    }
}