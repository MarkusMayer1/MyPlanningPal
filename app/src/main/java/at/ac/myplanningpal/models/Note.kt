package at.ac.myplanningpal.models

import java.time.LocalDate

data class Note(
    val id: Long? = null,
    val title: String,
    val date: String,
    val description: String? = null)

fun getNotesFromModel(): List<Note> {
    return listOf(
        Note(
            title = "Avatar",
            date = LocalDate.now().minusDays(1).toString(),
            description = "test description"),

        Note(
            title = "300",
            date = LocalDate.now().toString()),

        Note(
            title = "The Avengers",
            date = LocalDate.now().minusDays(2).toString()),

        Note(
            title = "The Wolf of Wall Street",
            date = LocalDate.of(2022, 1, 1).toString()),

        Note(
            title = "Interstellar",
            date = LocalDate.now().toString()),

        Note(
            title = "Game of Thrones",
            date = LocalDate.now().minusDays(4).toString()),

        Note(
            title = "Vikings",
            date = LocalDate.of(2022, 1, 1).toString()),

        Note(
            title = "Breaking Bad",
            date = LocalDate.of(2022, 1, 1).toString()),

        Note(
            title = "Narcos",
            date = LocalDate.now().toString()),
    )
}