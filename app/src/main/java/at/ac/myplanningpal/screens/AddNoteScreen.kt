package at.ac.myplanningpal.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.viewmodel.NoteViewModel
import java.time.LocalDate
import java.util.*

@Composable
fun AddNoteScreen(
    noteViewModel: NoteViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    note: Note? = null) {
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
        MainContentAddNoteScreen(navController = navController, noteViewModel = noteViewModel, note = note)
    }
}

@Composable
fun MainContentAddNoteScreen(
    navController: NavController = rememberNavController(),
    noteViewModel: NoteViewModel = viewModel(),
    note: Note? = null
) {
    var showMenu by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(note?.title ?: "") }
    var date by remember { mutableStateOf(note?.date ?: LocalDate.now().toString()) }
    var description by remember { mutableStateOf(note?.description ?: "") }
    var color by remember { mutableStateOf(note?.color ?: "") }

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
            value = description,
            label = { Text(text = "Description:") },
            onValueChange = {
                description = it
            }
        )

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if (note == null) {
                    if(title.isNotEmpty() && description.isNotEmpty()) {
                        val newNote = Note(
                            title = title,
                            date =  date,
                            description = description,
                            color = color)

                        noteViewModel.addNote(note = newNote)
                        navController.popBackStack()
                    }
                } else {
                    if(title.isNotEmpty() && description.isNotEmpty()) {
                        note.title = title
                        note.date = date
                        note.description = description
                        note.color = color

                        noteViewModel.editNote(note = note)
                        navController.popBackStack()
                    }
                }
            }) {

            if (note == null) {
                Text( text = "Save")
            } else {
                Text( text = "Update")
            }
        }
    }
}