package at.ac.myplanningpal.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType

@Composable
fun CalendarViewScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route =  MyPlanningPalScreens.AddAppointmentScreen.name) }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentCalendarView(appointmentViewModel = appointmentViewModel)
    }
}

@Composable
fun MainContentCalendarView(appointmentViewModel: AppointmentViewModel = viewModel()) {
    Kalendar(
        kalendarEvents = appointmentViewModel.getCalendarEvents(),
        kalendarType = KalendarType.Firey(),
        kalendarStyle = KalendarStyle(
            kalendarBackgroundColor = MaterialTheme.colors.primaryVariant,
            kalendarColor = MaterialTheme.colors.background,
            kalendarSelector = KalendarSelector.Circle(
                selectedColor = MaterialTheme.colors.primaryVariant,
                defaultColor = MaterialTheme.colors.background,
                todayColor = MaterialTheme.colors.primaryVariant
            )
        ),
        onCurrentDayClick = { localDate, kalendarEvent ->
            //handle the date click listener
        }, errorMessage = {
            //Handle the error if any
        })
}