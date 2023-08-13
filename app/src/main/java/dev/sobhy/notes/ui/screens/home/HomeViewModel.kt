package dev.sobhy.notes.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sobhy.notes.data.model.Note
import dev.sobhy.notes.repository.NotesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val context: Context): ViewModel() {
    private var _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes
    private val noteRepo by lazy {
        NotesRepository(context)
    }

    fun getNotes (): Job = viewModelScope.launch {
        _notes.update {
            noteRepo.getAllNotes()
        }
    }


}