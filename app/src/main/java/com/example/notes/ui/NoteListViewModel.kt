package com.example.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    fun addNote() {
        viewModelScope.launch {
            noteRepository.addNote(Note(0, "Test Title"))
        }
    }

}