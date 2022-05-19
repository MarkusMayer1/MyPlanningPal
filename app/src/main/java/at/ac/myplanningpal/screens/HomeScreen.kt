package at.ac.myplanningpal.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.widgets.BottomBarNavigation


@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MyPlanningPal") })
        },
        bottomBar = {
            BottomBarNavigation(navController = navController)
        }
    ) {
        MainContentHomeScreen()
    }
}

@Composable
fun MainContentHomeScreen() {

}