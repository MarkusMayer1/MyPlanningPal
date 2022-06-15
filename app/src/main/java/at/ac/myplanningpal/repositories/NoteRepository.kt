package at.ac.myplanningpal.repositories

import at.ac.myplanningpal.db.NotesDao
import at.ac.myplanningpal.models.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NotesDao) {

    suspend fun addNote(note: Note) = dao.addNote(note = note)

    suspend fun editNote(note: Note) = dao.editNote(note = note)

    suspend fun deleteNote(note: Note) = dao.deleteNote(note = note)

    fun getAllNotes(): Flow<List<Note>> = dao.getNotes()
}
