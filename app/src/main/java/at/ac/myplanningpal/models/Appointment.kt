package at.ac.myplanningpal.models

import java.time.LocalDate

data class Appointment(
    val id: String,
    val title: String,
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
    val alarm: Boolean = false)


fun getAppointmentsFromModel(): List<Appointment> {
    return listOf(
        Appointment(id = "tt0499549",
            title = "Avatar",
            date = LocalDate.now().minusDays(1),
            eventName = "test",
            eventDescription = "test description"),

        Appointment(id = "tt0416449",
            title = "300",
            date = LocalDate.now(),
            eventName = "test"),

        Appointment(id = "tt0848228",
            title = "The Avengers",
            date = LocalDate.now().minusDays(2),
            eventName = "test"),

        Appointment(id = "tt0993846",
            title = "The Wolf of Wall Street",
            date = LocalDate.of(2022, 1, 1),
            eventName = "test"),

        Appointment(id = "tt0816692",
            title = "Interstellar",
            date = LocalDate.now(),
            eventName = "test"),

        Appointment(id = "tt0944947",
            title = "Game of Thrones",
            date = LocalDate.now().minusDays(4),
            eventName = "test"),

        Appointment(id = "tt2306299",
            title = "Vikings",
            date = LocalDate.of(2022, 1, 1),
            eventName = "test"),

        Appointment(id = "tt0903747",
            title = "Breaking Bad",
            date = LocalDate.of(2022, 1, 1),
            eventName = "test"),

        Appointment(id = "tt2707408",
            title = "Narcos",
            date = LocalDate.now(),
            eventName = "test"),
        )
}