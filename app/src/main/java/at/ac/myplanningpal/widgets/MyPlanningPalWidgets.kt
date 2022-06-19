package at.ac.myplanningpal.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import java.time.LocalDate

@Composable
fun TodaysAppointmentRow(
    appointment: Appointment,
    onItemEditClick: (Appointment) -> Unit = {},
    onItemDeleteClick: (Appointment) -> Unit = {}
) {
    if (appointment.date == LocalDate.now().toString()) {
        AppointmentRow(
            appointment = appointment,
            onItemEditClick = onItemEditClick,
            onItemDeleteClick = onItemDeleteClick
        )
    }
}

@Composable
fun AppointmentRow(
    appointment: Appointment,
    onItemEditClick: (Appointment) -> Unit = {},
    onItemDeleteClick: (Appointment) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
        /*.clickable { onItemClick(appointment) }*/,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        when (appointment.color) {
        "Green" -> {
            Box(
                modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color.Green)
            )
        }
        "Yellow" -> {
            Box(
                modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFFFE500))
            )
        }
        "Red" -> {
            Box(
                modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color.Red)
            )
        }
    }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = appointment.title, style = MaterialTheme.typography.h6)
                Text(
                    text = "Date: ${appointment.date}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Time: ${appointment.time}",
                    style = MaterialTheme.typography.caption
                )
                if (!appointment.eventDescription.isNullOrEmpty()) {
                    Text(
                        text = "Description: ${appointment.eventDescription}",
                        style = MaterialTheme.typography.caption
                    )
                }
                if (appointment.alarm) {
                    Icon(imageVector = Icons.Default.AlarmOn, contentDescription = "alarmOn")
                } else {
                    Icon(imageVector = Icons.Default.AlarmOff, contentDescription = "alarmOff")
                }
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column {
                    IconButton(onClick = { onItemEditClick(appointment) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    IconButton(onClick = { onItemDeleteClick(appointment) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppointmentWithMonthAndDay(
    date: LocalDate,
    appointments: List<Appointment>,
    onItemEditClick: (Appointment) -> Unit = {},
    onItemDeleteClick: (Appointment) -> Unit = {}
) {
    Text(
        text = date.month.toString(),
        style = MaterialTheme.typography.h6
    )

    Row {
        Text(
            modifier = Modifier.width(25.dp),
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.End
        )

        Column {
            val appointmentsByDate = mutableListOf<Appointment>()
            for (appointment in appointments) {
                if (appointment.date == date.toString()) appointmentsByDate.add(appointment)
            }

            for (appointment in appointmentsByDate) {
                AppointmentRow(
                    appointment = appointment,
                    onItemEditClick = onItemEditClick,
                    onItemDeleteClick = onItemDeleteClick)
            }
        }
    }

    Spacer(modifier = Modifier.height(30.dp))
    Divider(
        color = MaterialTheme.colors.onSurface,
        thickness = 2.dp
    )
}

@Composable
fun NoteRow(
    note: Note,
    onItemEditClick: (Note) -> Unit = {},
    onItemDeleteClick: (Note) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
        /*.clickable { onItemClick(appointment) }*/,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        when (note.color) {
            "Green" -> {
                Box(
                    modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color.Green)
                )
            }
            "Yellow" -> {
                Box(
                    modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFFFE500))
                )
            }
            "Red" -> {
                Box(
                    modifier = Modifier.height(5.dp).width(75.dp).clip(RoundedCornerShape(12.dp)).background(Color.Red)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = note.title, style = MaterialTheme.typography.h6)
                Text(
                    text = "Date: ${note.date}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Description: ${note.description}",
                    style = MaterialTheme.typography.caption
                )
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column {
                    IconButton(onClick = { onItemEditClick(note) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    IconButton(onClick = { onItemDeleteClick(note) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }
            }
        }
    }
}