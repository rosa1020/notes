package com.example.notes.ui.noteeditscreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepository
import com.example.notes.data.db.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldData(label = "Title"))
    val noteTitle: State<NoteTextFieldData> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldData(label = "Content"))
    val noteContent: State<NoteTextFieldData> = _noteContent

    private var currentId: Int? = null

    private var currentNote: Note? = null

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    getNote(id)?.also { note ->
                        currentNote = note
                        currentId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                        )

                    }
                }
            }
        }
    }

    fun updateTitle(newTitle: String) {
        _noteTitle.value = noteTitle.value.copy(text = newTitle)
    }

    fun updateContent(newContent: String) {
        _noteContent.value = noteContent.value.copy(text = newContent)
    }

    fun saveNote() {
        viewModelScope.launch {
            val newNote = Note(
                title = noteTitle.value.text,
                content = noteContent.value.text,
                timestamp = System.currentTimeMillis(),
                id = currentId
            )
            noteRepository.addNote(newNote)
        }
    }

    private suspend fun getNote(id: Int): Note? {
        return noteRepository.getNote(id)
    }
}