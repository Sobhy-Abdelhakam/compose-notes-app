package dev.sobhy.notes.ui.screens.note

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sobhy.notes.data.model.Note
import dev.sobhy.notes.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(private val context: Context) : ViewModel() {
    private val notesRepository by lazy {
        NotesRepository(context)
    }
    private val _note = MutableStateFlow<Note>(Note(0, "", ""))
    var note = _note

    fun saveNote(note: Note) = viewModelScope.launch {
        notesRepository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }

    fun getNote(noteId: Int) = viewModelScope.launch {
        _note.update {
            notesRepository.getNote(noteId)
        }
    }

}
