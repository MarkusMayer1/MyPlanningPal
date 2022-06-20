package at.ac.myplanningpal.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    var title: String,
    var description: String,
    var date: String,
    var importance: String,
)

//fun getNotesFromModel(): List<Note> {
//    return listOf(
//        Note(
//            title = "Avatar",
//            date = LocalDate.now().minusDays(1).toString(),
//            description = "test description"),
//
//        Note(
//            title = "300",
//            date = LocalDate.now().toString()),
//
//        Note(
//            title = "The Avengers",
//            date = LocalDate.now().minusDays(2).toString()),
//
//        Note(
//            title = "The Wolf of Wall Street",
//            date = LocalDate.of(2022, 1, 1).toString()),
//
//        Note(
//            title = "Interstellar",
//            date = LocalDate.now().toString()),
//
//        Note(
//            title = "Game of Thrones",
//            date = LocalDate.now().minusDays(4).toString()),
//
//        Note(
//            title = "Vikings",
//            date = LocalDate.of(2022, 1, 1).toString()),
//
//        Note(
//            title = "Breaking Bad",
//            date = LocalDate.of(2022, 1, 1).toString()),
//
//        Note(
//            title = "Narcos",
//            date = LocalDate.now().toString()),
//    )
//}