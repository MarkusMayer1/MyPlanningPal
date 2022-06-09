package at.ac.myplanningpal.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import at.ac.myplanningpal.models.Appointment
//import at.ac.myplanningpal.models.getAppointmentsFromModel
import at.ac.myplanningpal.models.Note
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AppointmentRow(
    appointment: Appointment,
    onItemClick: (Appointment) -> Unit = {}
    /*onItemEditClick: (Appointment) -> Unit = {},
    onItemDeleteClick: (Appointment) -> Unit = {}*/
) {
    /*var showExtendedMovieRow by remember {
        mutableStateOf(false)
    }*/

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
        /*.clickable { onItemClick(appointment) }*/,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
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
                    text = "Event Name: ${appointment.eventName}",
                    style = MaterialTheme.typography.caption
                )
                if (!appointment.eventDescription.isNullOrEmpty()) {
                    Text(
                        text = "Event Description: ${appointment.eventDescription}",
                        style = MaterialTheme.typography.caption
                    )
                }

                /*Icon(
                    imageVector = if (showExtendedMovieRow) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "arrow",
                    modifier = Modifier.clickable { showExtendedMovieRow = !showExtendedMovieRow }
                )*/
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onItemClick(appointment) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colors.secondary
                    )
                }
                /*Column {
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
                }*/
            }
        }
    }
}


@Composable
fun AppointmentWithMonthAndDay(
    stringDate: String,
    appointments: List<Appointment>,
    onItemClick: (Appointment) -> Unit = {}
    /*onItemEditClick: (Appointment) -> Unit = {},
    onItemDeleteClick: (Appointment) -> Unit = {}*/
) {
    val date = LocalDate.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE)

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
                AppointmentRow(appointment = appointment, onItemClick = onItemClick)
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
    onItemClick: (Note) -> Unit = {}
    /*onItemEditClick: (Note) -> Unit = {},
    onItemDeleteClick: (Note) -> Unit = {}*/
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
        /*.clickable { onItemClick(appointment) }*/,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
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
                IconButton(onClick = { onItemClick(note) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colors.secondary
                    )
                }
                /*Column {
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
                }*/
            }
        }
    }
}