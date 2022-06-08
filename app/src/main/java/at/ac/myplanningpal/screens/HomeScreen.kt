package at.ac.myplanningpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.widgets.AppointmentRow

@Composable
fun HomeScreen(appointmentViewModel: AppointmentViewModel = viewModel()) {
    MainContentHomeScreen(appointmentViewModel = appointmentViewModel)
}

@Composable
fun MainContentHomeScreen(appointmentViewModel: AppointmentViewModel = viewModel()) {
    Column {
        Text(text = "Today's Appointments: ", style = MaterialTheme.typography.h5)

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(items = appointmentViewModel.getTodaysAppointments()) { appointment ->
                AppointmentRow(
                    appointment = appointment,
                    onItemClick = { appointmentToDelete ->
                        appointmentViewModel.removeAppointment(appointmentToDelete)
                    }
                )
            }
        }
    }
}