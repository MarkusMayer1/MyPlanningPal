package at.ac.myplanningpal.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.viewmodel.NoteViewModel
import java.time.LocalDate
import java.util.*

@Composable
fun AddNoteScreen(noteViewModel: NoteViewModel = viewModel(), navController: NavController = rememberNavController()) {
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
        MainContentAddNoteScreen(navController = navController, noteViewModel = noteViewModel)
    }
}

@Composable
fun MainContentAddNoteScreen(
    navController: NavController = rememberNavController(),
    noteViewModel: NoteViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var date = remember { mutableStateOf(LocalDate.now().toString()) }
    var description by remember { mutableStateOf("") }

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
            value = description,
            label = { Text(text = "Description:") },
            onValueChange = {
                description = it
            }
        )

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()){
                    val newNote = Note(
                        title = title,
                        date =  date.value,
                        description = description)

                    noteViewModel.addNote(newNote)
                    navController.popBackStack()
                }

            }) {

            Text( text = "Save")
        }
    }
}