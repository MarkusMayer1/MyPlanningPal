package at.ac.myplanningpal.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import java.time.LocalDate
import java.util.*

@Composable
fun AddAppointmentScreen(appointmentViewModel: AppointmentViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "arrow back"
                    )
                }
            })
        }
    ) {
        MainContentAddAppointmentScreen(navController = navController, appointmentViewModel = appointmentViewModel)
    }
}

@Composable
fun MainContentAddAppointmentScreen(
    navController: NavController = rememberNavController(),
    appointmentViewModel: AppointmentViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var date = remember { mutableStateOf(LocalDate.now().toString()) }
    var eventName by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }
    var alarm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            label = { Text(text = "Title:") },
            onValueChange = {
                title = it
            }
        )

        val context = LocalContext.current

        val year: Int
        val month: Int
        val day: Int

        val mCalendar = Calendar.getInstance()

        year = mCalendar.get(Calendar.YEAR)
        month = mCalendar.get(Calendar.MONTH)
        day = mCalendar.get(Calendar.DAY_OF_MONTH)

        mCalendar.time = Date()

        val mDatePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                if (month < 10 && dayOfMonth < 10) {
                    date.value = "$year-0${month + 1}-0$dayOfMonth"
                } else if (month < 10) {
                    date.value = "$year-0${month + 1}-$dayOfMonth"
                } else if (dayOfMonth < 10) {
                    date.value = "$year-${month + 1}-0$dayOfMonth"
                } else {
                    date.value = "$year-${month + 1}-$dayOfMonth"
                }
            }, year, month, day
        )

        Column {
            Row {
                OutlinedTextField(
                    modifier = Modifier.clickable { mDatePickerDialog.show() },
                    enabled = false,
                    value = date.value,
                    label = { Text(text = "Date:") },
                    onValueChange = {
                        date.value = it
                    }
                )
            }
        }

        OutlinedTextField(
            value = eventName,
            label = { Text(text = "Event name:") },
            onValueChange = {
                eventName = it
            }
        )

        OutlinedTextField(
            value = eventDescription,
            label = { Text(text = "Description:") },
            onValueChange = {
                eventDescription = it
            }
        )

        Row {
            Text(text = "Alarm: ")
            Switch(checked = alarm, onCheckedChange = { alarm = it })
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if(title.isNotEmpty() && eventName.isNotEmpty()){
                    val newAppointment = Appointment(
                        title = title,
                        date =  date.value,
                        eventName = eventName,
                        eventDescription = eventDescription,
                        alarm = alarm)

                    appointmentViewModel.addAppointment(newAppointment)
                    navController.popBackStack()
                }

            }) {

            Text( text = "Save")
        }
    }
}