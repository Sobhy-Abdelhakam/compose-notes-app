package dev.sobhy.notes.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sobhy.notes.data.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current

    val homeViewModel by lazy {
        HomeViewModel(context)
    }
    val noteList by homeViewModel.notes.collectAsState()
//    val noteDao by lazy {
//        NotesDB.invoke(context).noteDao()
//    }
//    var notesList = remember {
//        mutableStateOf(homeViewModel.getNotes())
//    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Notes")
            }, actions = {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("note")
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
//        notesList.value = homeViewModel.getNotes()
        homeViewModel.getNotes()
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                items(noteList) {
                    Spacer(modifier = Modifier.height(8.dp))
                    NoteItem(note = it, navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(onClick = {
        navController.navigate("note/${note.id}")
    }, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(6.dp)) {
            note.subject?.let {
                Text(text = it, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.content)
        }

    }
}
