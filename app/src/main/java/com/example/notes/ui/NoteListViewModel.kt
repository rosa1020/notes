package com.example.notes.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _state = mutableStateOf(NoteUIData())
    val state: State<NoteUIData> = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        _state.value.isLoading = true
        viewModelScope.launch {
            noteRepository.getNotes().onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes
                )
            }
            _state.value.isLoading = false
        }
    }
}