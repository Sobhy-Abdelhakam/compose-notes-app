package dev.sobhy.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.sobhy.notes.ui.screens.StartScreen
import dev.sobhy.notes.ui.screens.home.HomeScreen
import dev.sobhy.notes.ui.screens.note.NoteScreen
import dev.sobhy.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "start"
                ) {
                    composable("start") {
                        StartScreen(navController)
                    }
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable(
                        "note/{id}",
                        arguments = (listOf(navArgument("id") { type = NavType.IntType }))
                    ) {
                        val noteId = it.arguments?.getInt("id")
                        NoteScreen(navController = navController, noteId)
                    }
                }

            }
        }
    }
}