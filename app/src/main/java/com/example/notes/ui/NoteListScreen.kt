package com.example.notes.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteListScreen(noteListViewModel: NoteListViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {TopAppBar(title = { Text(text = "notes")})},
        floatingActionButton = {
            FloatingActionButton(
                onClick = { noteListViewModel.addNote() },
                backgroundColor = Color.Green) {
                Text(text = "new")
            }
        }
    ) {
        Text(text = "Welcome, bienvenue, willkommen im Kabaret!")
    }
}

@Preview
@Composable
fun NoteListScreenPreview() {
    NoteListScreen()
}
