package com.example.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.data.db.Note
import com.example.notes.navigation.Screen

@Composable
fun NoteListScreen(navController: NavController, noteListViewModel: NoteListViewModel = hiltViewModel()) {
    val state = noteListViewModel.state.value

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new note")
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?id=${note.id}"
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text (
            text = note.title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text (
            text = note.content,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis
        )
    }
}