package at.ac.myplanningpal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
    Column(Modifier.fillMaxSize().padding(top =  10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Calendar: ", style = MaterialTheme.typography.h5)

        Divider()

        Spacer(modifier = Modifier.height(10.dp))
        Kalendar(
            kalendarEvents = appointmentViewModel.getCalendarEvents(),
            kalendarType = KalendarType.Firey(),
            kalendarStyle = KalendarStyle(
                kalendarBackgroundColor = MaterialTheme.colors.background,
                kalendarColor = MaterialTheme.colors.background,
                kalendarSelector = KalendarSelector.Circle(
                    selectedColor = MaterialTheme.colors.primary,
                    defaultColor = MaterialTheme.colors.primaryVariant,
                    todayColor = MaterialTheme.colors.primaryVariant,
                    defaultTextColor = MaterialTheme.colors.secondaryVariant
                ),
                elevation = 10.dp
            ),
            onCurrentDayClick = { localDate, kalendarEvent ->
                //handle the date click listener
            }, errorMessage = {
                //Handle the error if any
            })
    }
}