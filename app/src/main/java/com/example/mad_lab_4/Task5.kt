package com.example.mad_lab_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.ai.client.generativeai.type.content
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.collectAsState



class Task5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val noteViewModel: NoteViewModel = viewModel()
            val text = remember { mutableStateOf("") }

            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text("Add a note") }
                )
                Button(
                    onClick = {
                        noteViewModel.addNewNote(text.value)
                        text.value = ""
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Save")
                }
                NoteList(noteViewModel = noteViewModel)
            }
        }
    }

    @Composable
    fun NoteList(noteViewModel: NoteViewModel) {
        val notes by noteViewModel.notes.observeAsState(initial = emptyList())


        LazyColumn {
            items(notes) { note ->
                Text(text = note.content)
            }
        }
    }

}
