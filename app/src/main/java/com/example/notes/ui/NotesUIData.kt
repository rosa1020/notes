package com.example.notes.ui

import com.example.notes.data.db.Note

data class NotesUIData(
    val notes: List<Note>,
    val isLoading: Boolean
)
