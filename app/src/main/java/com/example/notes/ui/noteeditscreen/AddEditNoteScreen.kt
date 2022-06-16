package com.example.notes.ui.noteeditscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.ui.navigation.Screen

@Composable
fun AddEditNoteScreen(navController: NavController, addEditViewModel: AddEditViewModel = hiltViewModel()) {
    val titleData = addEditViewModel.noteTitle.value
    val contentData = addEditViewModel.noteContent.value

    Scaffold(
        topBar = {
                 TopAppBar(
                     title = { Text(text = addEditViewModel.getScreenTitle()) },
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
                                 addEditViewModel.saveNote()
                                 navController.navigate(Screen.NoteListScreen.route)
                             }
                         ) {
                             Icon(imageVector = Icons.Filled.Save, contentDescription = "Save note")
                         }
                     }
                 )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            TextField(
                value = titleData.text,
                onValueChange = { addEditViewModel.updateTitle(it) },
                label = { Text(text = titleData.label) },
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = contentData.text,
                onValueChange = { addEditViewModel.updateContent(it) },
                label = { Text(text = contentData.label) },
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}