package com.example.notes.navigation

sealed class Screen(val route: String) {
    object NoteListScreen: Screen("note_list_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}