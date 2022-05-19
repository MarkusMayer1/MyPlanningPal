package at.ac.myplanningpal.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.widgets.BottomBarNavigation

@Composable
fun NoteScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MyPlanningPal") })
        },
        bottomBar = {
            BottomBarNavigation(navController = navController)
        }
    ) {
        MainContentNoteScreen()
    }
}

@Composable
fun MainContentNoteScreen() {

}