package com.example.notes.ui.notelistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.data.db.Note
import com.example.notes.ui.navigation.Screen

@Composable
fun NoteListScreen(navController: NavController, noteListViewModel: NoteListViewModel = hiltViewModel()) {
    val state = noteListViewModel.state.value

    Scaffold (
        topBar = {
          TopAppBar(
              title = { Text(text = "Your Notes (${state.notes.size})") }
          )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new note")
            }
        },
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
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.LightGray)
                            .clickable {
                                navController.navigate(
                                    Screen.NoteDetailScreen.route +
                                            "?id=${note.id}"
                                )
                            },
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
    modifier: Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}