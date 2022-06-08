package at.ac.myplanningpal.models

import java.time.LocalDate

data class Appointment(
    val id: Long? = null,
    val title: String,
    val date: String,
    val eventName: String,
    val eventDescription: String? = null,
    val alarm: Boolean = false)


fun getAppointmentsFromModel(): List<Appointment> {
    return listOf(
        Appointment(
            title = "Avatar",
            date = LocalDate.now().minusDays(1).toString(),
            eventName = "test",
            eventDescription = "test description"),

        Appointment(
            title = "300",
            date = LocalDate.now().toString(),
            eventName = "test"),

        Appointment(
            title = "The Avengers",
            date = LocalDate.now().minusDays(2).toString(),
            eventName = "test"),

        Appointment(
            title = "The Wolf of Wall Street",
            date = LocalDate.of(2022, 1, 1).toString(),
            eventName = "test"),

        Appointment(
            title = "Interstellar",
            date = LocalDate.now().toString(),
            eventName = "test"),

        Appointment(
            title = "Game of Thrones",
            date = LocalDate.now().minusDays(4).toString(),
            eventName = "test"),

        Appointment(
            title = "Vikings",
            date = LocalDate.of(2022, 1, 1).toString(),
            eventName = "test"),

        Appointment(
            title = "Breaking Bad",
            date = LocalDate.of(2022, 1, 1).toString(),
            eventName = "test"),

        Appointment(
            title = "Narcos",
            date = LocalDate.now().toString(),
            eventName = "test"),
        )
}