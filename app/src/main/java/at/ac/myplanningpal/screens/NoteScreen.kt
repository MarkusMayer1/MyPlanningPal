package at.ac.myplanningpal.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.AppointmentViewModel
import at.ac.myplanningpal.viewmodel.NoteViewModel
import at.ac.myplanningpal.widgets.NoteRow

@Composable
fun NoteScreen(noteViewModel: NoteViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route =  MyPlanningPalScreens.AddNoteScreen.name) }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentNoteScreen(noteViewModel = noteViewModel)
    }
}

@Composable
fun MainContentNoteScreen(noteViewModel: NoteViewModel = viewModel()) {
    LazyColumn {
        items(items = noteViewModel.getNotes()) { note ->
            NoteRow(
                note = note,
                onItemClick = { note ->
                    noteViewModel.removeNote(note)
                }
            )
        }
    }
}