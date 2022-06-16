package com.example.notes.ui.notedetailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteState = mutableStateOf(NoteData())
    val noteState: State<NoteData> = _noteState

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    noteRepository.getNote(id)?.also { note ->
                        _noteState.value = noteState.value.copy(
                            note = note
                        )
                    }
                }
            }
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            noteRepository.removeNote(note)
        }
    }
}
