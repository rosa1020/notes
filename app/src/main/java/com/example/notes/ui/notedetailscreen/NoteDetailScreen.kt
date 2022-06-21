package com.example.notes.ui.notedetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.ui.navigation.Screen

@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteDetailViewModel: NoteDetailViewModel = hiltViewModel()
) {

    val noteData = noteDetailViewModel.noteState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Note Details")},
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else {
                    null
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(
                                Screen.AddEditNoteScreen.route + "?id=${noteData.note.id}"
                            )
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit note")
                    }
                    IconButton(
                        onClick = {
                            noteDetailViewModel.removeNote(noteData.note)
                            navController.navigate(Screen.NoteListScreen.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete note")
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = noteData.note.title,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = noteData.note.content,
                style = MaterialTheme.typography.body1
            )
        }
    }
}