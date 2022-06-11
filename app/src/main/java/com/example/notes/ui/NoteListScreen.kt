package com.example.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.data.db.Note

@Composable
fun NoteListScreen(noteListViewModel: NoteListViewModel = hiltViewModel()) {

    val noteListUIData by noteListViewModel.notes.collectAsState()

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
        NoteListScreen(
            notes = noteListUIData.notes,
            isLoading = noteListUIData.isLoading,
            onItemClicked = { noteItem -> noteListViewModel.removeNote(noteItem) }
        )
    }
}

@Composable
fun NoteListScreen(
    notes: List<Note>,
    isLoading: Boolean,
    onItemClicked: (item: Note) -> Unit,
) {
    if (isLoading) {
        LoadingContent()
    } else {
        NoteListContent(notes = notes, onItemClicked = onItemClicked)
    }
}

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun NoteListContent(
    notes: List<Note>,
    onItemClicked: (item: Note) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes) { item ->
                NoteListElement(note = item, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
fun NoteListElement(note: Note, onItemClicked: (item: Note) -> Unit) {
    Row(modifier = Modifier.clickable { onItemClicked(note) }.padding(8.dp).fillMaxWidth()) {
        Text(text = "Note #${note.id}: ${note.title}")
    }
}

@Preview
@Composable
fun NoteListScreenPreview() {
    NoteListScreen()
}
