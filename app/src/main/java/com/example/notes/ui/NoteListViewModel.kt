package com.example.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow(DEFAULT_NOTELIST_UI_DATA)
    val notes = _notes.asStateFlow()

    init {
        loadNotes()
    }

    fun addNote() {
        viewModelScope.launch {
            noteRepository.addNote(Note(0, "Test Title"))
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            noteRepository.removeNote(note)
        }
    }

    private fun loadNotes() {
        _notes.update { prev -> prev.copy(isLoading = true) }
        viewModelScope.launch {
            val notes = noteRepository.getNotes()
            _notes.update { prev -> prev.copy(isLoading = false, notes = notes) }
        }
    }

    companion object {
        private val DEFAULT_NOTELIST_UI_DATA = NotesUIData(mutableListOf<Note>(), false)
    }

}