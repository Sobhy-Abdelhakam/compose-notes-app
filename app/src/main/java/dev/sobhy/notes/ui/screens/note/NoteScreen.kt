package dev.sobhy.notes.ui.screens.note

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.sobhy.notes.data.model.Note

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController: NavController, noteId: Int?) {
    val context = LocalContext.current
    val viewModel by lazy {
        NoteViewModel(context)
    }

    lateinit var currentNote: Note
    noteId?.let {
        viewModel.getNote(it)
        currentNote = viewModel.note.value
    }

//    val noteDataBase by lazy {
//        NotesDB
//    }
    var title by remember {
        mutableStateOf(currentNote.subject ?: "")
    }
    var content by remember {
        mutableStateOf(currentNote.content ?: "")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.White)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                    Text(text = "Back", color = Color.Blue)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    viewModel.saveNote(Note(subject = title, content = content))
//                    noteDataBase.invoke(context).noteDao()
//                        .insertNote(Note(subject = title, content = content))
                    navController.popBackStack()
                }) {
                    Text(text = "Save")
                }
            }
            Divider()
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    if (currentNote.subject == null){
                        Text(
                            text = "Title",
                            fontSize = 20.sp
                        )
                    }

                })
            Divider()
            TextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                label = {
                    if(currentNote.content == null){
                        Text(text = "Content", fontSize = 18.sp)
                    }

                }
            )
        }

    }
}