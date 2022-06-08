package at.ac.myplanningpal.models

import java.time.LocalDate

data class Note(
    val id: String,
    val title: String,
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
    val alarm: Boolean = false)