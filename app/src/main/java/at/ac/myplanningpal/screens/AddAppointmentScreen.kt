package at.ac.myplanningpal.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun AddAppointmentScreen(
    appointmentViewModel: AppointmentViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    appointment: Appointment? = null) {
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
        MainContentAddAppointmentScreen(
            navController = navController,
            appointmentViewModel = appointmentViewModel,
            appointment = appointment)
    }
}

@Composable
fun MainContentAddAppointmentScreen(
    navController: NavController = rememberNavController(),
    appointmentViewModel: AppointmentViewModel = viewModel(),
    appointment: Appointment? = null
) {
    var showMenu by remember { mutableStateOf(false) }

    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    var title by remember { mutableStateOf(appointment?.title ?: "") }
    var date by remember { mutableStateOf(appointment?.date ?: LocalDate.now().toString()) }
    var time by remember { mutableStateOf(appointment?.time ?: current.format(formatter)) }
    var eventDescription by remember { mutableStateOf(appointment?.eventDescription ?: "") }
    var color by remember { mutableStateOf(appointment?.color ?: "") }
    var alarm by remember { mutableStateOf(appointment?.alarm ?: false) }

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
                    date = "$year-0${month + 1}-0$dayOfMonth"
                } else if (month < 10) {
                    date = "$year-0${month + 1}-$dayOfMonth"
                } else if (dayOfMonth < 10) {
                    date = "$year-${month + 1}-0$dayOfMonth"
                } else {
                    date = "$year-${month + 1}-$dayOfMonth"
                }
            }, year, month, day
        )

        Column {
            Row {
                OutlinedTextField(
                    modifier = Modifier.clickable { mDatePickerDialog.show() },
                    enabled = false,
                    value = date,
                    label = { Text(text = "Date:") },
                    onValueChange = {
                        date = it
                    }
                )
            }
        }

        val hour = mCalendar[Calendar.HOUR_OF_DAY]
        val minute = mCalendar[Calendar.MINUTE]

        val mTimePickerDialog = TimePickerDialog(
            context,
            {_, hour : Int, minute: Int ->
                if (hour < 10 && minute < 10) {
                    time = "0$hour:0$minute"
                } else if (hour < 10) {
                    time = "0$hour:$minute"
                } else if (minute < 10) {
                    time = "$hour:0$minute"
                } else {
                    time = "$hour:$minute"
                }
            }, hour, minute, true
        )

        Column {
            Row {
                OutlinedTextField(
                    modifier = Modifier.clickable { mTimePickerDialog.show() },
                    enabled = false,
                    value = time,
                    label = { Text(text = "Time:") },
                    onValueChange = {
                        time = it
                    }
                )
            }
        }

        Column {
            OutlinedTextField(
                modifier = Modifier.clickable { showMenu = !showMenu },
                enabled = false,
                value = color,
                label = { Text(text = "Importance:") },
                onValueChange = {
                    color = it
                }
            )

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {

                DropdownMenuItem(onClick = {
                    color = "Very important"
                    showMenu = false
                }) {
                    Row {
                        Text(
                            text = "Very important",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(130.dp)
                        )
                        Canvas(modifier = Modifier.size(25.dp), onDraw = {
                            drawCircle(color = Color.Red)
                        })
                    }
                }

                DropdownMenuItem(onClick = {
                    color = "Important"
                    showMenu = false
                }) {
                    Row {
                        Text(
                            text = "Important",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(130.dp)
                        )
                        Canvas(modifier = Modifier.size(25.dp), onDraw = {
                            drawCircle(color = Color.Yellow)
                        })
                    }
                }

                DropdownMenuItem(onClick = {
                    color = "Not important"
                    showMenu = false
                }) {
                    Row {
                        Text(
                            text = "Not important",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(130.dp)
                        )
                        Canvas(modifier = Modifier.size(25.dp), onDraw = {
                            drawCircle(color = Color.Green)
                        })
                    }
                }
            }
        }

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
                if (appointment == null) {
                    if(title.isNotEmpty()){
                        val newAppointment = Appointment(
                            title = title,
                            date =  date,
                            time = time,
                            eventName = title,
                            color = color,
                            eventDescription = eventDescription,
                            alarm = alarm)

                        appointmentViewModel.addAppointment(newAppointment)
                        navController.popBackStack()
                    }
                } else {
                    if(title.isNotEmpty()) {
                        appointment.title = title
                        appointment.date = date
                        appointment.time = time
                        appointment.eventName = title
                        appointment.color = color
                        appointment.eventDescription = eventDescription
                        appointment.alarm = alarm

                        appointmentViewModel.editAppointment(appointment = appointment)
                        navController.popBackStack()
                    }
                }
            }
        ) {
            if (appointment == null) {
                Text( text = "Save")
            } else {
                Text( text = "Update")
            }
        }
    }
}