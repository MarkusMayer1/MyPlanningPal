package at.ac.myplanningpal.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.viewmodel.NoteViewModel
import at.ac.myplanningpal.widgets.NoteRow

@Composable
fun NoteScreen(noteViewModel: NoteViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            Log.d("dawdffgsdgva",navController.toString())
            Log.d("dawdffgsdgva", MyPlanningPalScreens.AddNoteScreen.name)
            Log.d("dawdffgsdgva", MyPlanningPalScreens.AddNoteScreen.name)
            FloatingActionButton(onClick = { navController.navigate(route = MyPlanningPalScreens.AddNoteScreen.name) }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentNoteScreen(noteViewModel = noteViewModel)
    }
}

@Composable
fun MainContentNoteScreen(noteViewModel: NoteViewModel = viewModel()) {
    val notes: List<Note> by noteViewModel.notes.collectAsState()
    LazyColumn {
        items(items = notes) { note ->
            NoteRow(
                note = note,
                onItemClick = { beidl ->
                    noteViewModel.removeNote(beidl)
                }
            )
        }
    }
}