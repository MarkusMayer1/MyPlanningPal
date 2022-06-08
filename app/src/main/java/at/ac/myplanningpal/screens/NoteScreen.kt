package at.ac.myplanningpal.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun NoteScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentNoteScreen()
    }
}

@Composable
fun MainContentNoteScreen() {
    Text(text = "note screen")
}