package com.example.notes.ui.notedetailscreen

import com.example.notes.data.db.Note

data class NoteData(
    val note: Note = Note("", "", 0),
)