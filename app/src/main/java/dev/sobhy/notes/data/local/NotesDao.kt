package dev.sobhy.notes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.sobhy.notes.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from notes")
    suspend fun getAllNotes(): List<Note>

    @Query("select * from notes where id = :noteId")
    suspend fun getNoteById(noteId: Int): Note

}