package at.ac.myplanningpal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.navigation.MyPlanningPalScreens
import at.ac.myplanningpal.viewmodel.NoteViewModel
import at.ac.myplanningpal.widgets.NoteRow
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun NoteScreen(noteViewModel: NoteViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = MyPlanningPalScreens.AddNoteScreen.name) }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentNoteScreen(noteViewModel = noteViewModel, navController = navController)
    }
}

@Composable
fun MainContentNoteScreen(noteViewModel: NoteViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Notes: ", style = MaterialTheme.typography.h5)

        Divider()

        Spacer(modifier = Modifier.height(10.dp))
        val notes: List<Note> by noteViewModel.notes.collectAsState()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Note::class.java).lenient()

        LazyColumn {
            items(items = notes) { note ->
                NoteRow(
                    note = note,
                    onItemEditClick = { editNote ->
                        val noteJson = jsonAdapter.toJson(editNote)
                        navController.navigate(route = MyPlanningPalScreens.AddNoteScreen.name + "?note=$noteJson")
                    },
                    onItemDeleteClick = { deleteNote ->
                        noteViewModel.removeNote(deleteNote)
                    }
                )
            }
        }
        IconButton(onClick = { navController.navigate(route = MyPlanningPalScreens.DrawingScreen.name) }) {
            Icon(imageVector = Icons.Default.BorderColor, contentDescription = "draw")
        }
    }
}