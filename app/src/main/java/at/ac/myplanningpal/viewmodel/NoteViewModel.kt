package at.ac.myplanningpal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import at.ac.myplanningpal.models.Note
import at.ac.myplanningpal.models.getNotesFromModel


class NoteViewModel : ViewModel() {
    //private val _appointments = mutableStateListOf<Appointment>()

    private var _notes = mutableStateListOf<Note>()
    init {
        for (note in getNotesFromModel().toMutableStateList()) {
            addNote(note)
        }
    }

    fun getNotes(): List<Note> {
        return _notes
    }

    fun addNote(note: Note) {
        _notes.add(note)
    }

    fun removeNote(note: Note) {
        _notes.remove(note)
    }
}