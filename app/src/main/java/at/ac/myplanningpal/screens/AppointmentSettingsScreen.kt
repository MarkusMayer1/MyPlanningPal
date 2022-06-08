package at.ac.myplanningpal.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun AppointmentScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        MainContentAppointmentSettingsScreen()
    }
}

@Composable
fun MainContentAppointmentSettingsScreen() {
    Text(text = "appointment settings screen")
}