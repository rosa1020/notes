package com.example.notes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.ui.AddEditNoteScreen
import com.example.notes.ui.NoteListScreen
import com.example.notes.ui.notedetailscreen.NoteDetailScreen

@Composable
fun NotesNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.NoteListScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(route = Screen.NoteListScreen.route) {
            NoteListScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditNoteScreen.route + "?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditNoteScreen(navController = navController)
        }
        composable(
            route = Screen.NoteDetailScreen.route + "?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            NoteDetailScreen(navController = navController)
        }
    }
}