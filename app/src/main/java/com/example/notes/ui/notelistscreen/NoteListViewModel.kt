package com.example.notes.ui.notelistscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _state = mutableStateOf(NoteListData())
    val state: State<NoteListData> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = getSortedNotes()
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getSortedNotes() : Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            notes.sortedByDescending { it.timestamp }
        }
    }
}