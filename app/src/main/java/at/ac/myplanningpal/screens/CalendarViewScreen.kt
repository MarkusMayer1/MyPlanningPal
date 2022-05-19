package at.ac.myplanningpal.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.myplanningpal.widgets.BottomBarNavigation
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType

@Composable
fun CalendarViewScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MyPlanningPal") })
        },
        bottomBar = {
            BottomBarNavigation(navController = navController)
        }
    ) {
        MainContentCalendarView()
    }
}

@Composable
fun MainContentCalendarView() {
    Kalendar(
        kalendarType = KalendarType.Firey(),
        kalendarStyle = KalendarStyle(
            kalendarBackgroundColor = MaterialTheme.colors.primaryVariant,
            kalendarColor = MaterialTheme.colors.background,
            kalendarSelector = KalendarSelector.Circle(
                selectedColor = MaterialTheme.colors.primaryVariant,
                defaultColor = MaterialTheme.colors.background,
                todayColor = MaterialTheme.colors.primaryVariant
            )
        ),
        onCurrentDayClick = { day, event ->
            //handle the date click listener
        }, errorMessage = {
            //Handle the error if any
        })
}