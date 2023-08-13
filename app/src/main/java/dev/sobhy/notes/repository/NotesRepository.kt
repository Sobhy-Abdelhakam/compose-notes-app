package dev.sobhy.notes.repository

import android.content.Context
import dev.sobhy.notes.data.local.NotesDB
import dev.sobhy.notes.data.model.Note

class NotesRepository(private val context: Context) {
    private val db by lazy {
        NotesDB.invoke(context)
    }

    suspend fun insertNote(note: Note) = db.noteDao().insertNote(note)

    suspend fun deleteNote(note: Note) = db.noteDao().deleteNote(note)

    suspend fun getAllNotes(): List<Note> = db.noteDao().getAllNotes()

    suspend fun getNote(noteId: Int): Note = db.noteDao().getNoteById(noteId)

}