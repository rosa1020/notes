package com.example.notes.ui.notelistscreen

import com.example.notes.data.db.Note

data class NoteUIData(
    val notes: List<Note> = emptyList(),
    var isLoading: Boolean = false
) {
}