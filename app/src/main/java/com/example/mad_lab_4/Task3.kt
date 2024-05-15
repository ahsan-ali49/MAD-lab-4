package com.example.mad_lab_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_lab_4.ui.theme.MADlab4Theme
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter

class Task3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADlab4Theme {
                Task3Screen()
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Task3Screen() {
        val text = remember { mutableStateOf("") }
        val notes = remember { mutableStateOf(listOf<String>()) }

        LaunchedEffect(Unit) {
            notes.value = getListOfNotes()
        }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Note Taking App") })
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text("Enter a note") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        saveNote(text.value)
                        notes.value = getListOfNotes()
                        text.value = ""
                    },
                    enabled = text.value.isNotBlank(),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save Note")
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(notes.value) { note ->
                        Text(note, style = MaterialTheme.typography.bodyLarge)
                        Divider()
                    }
                }
            }
        }
    }

    private fun saveNote(text: String) {
        val file = File(filesDir, System.currentTimeMillis().toString())
        PrintWriter(file).use { it.println(text) }
    }

    private fun getListOfNotes(): List<String> {
        return filesDir.listFiles()?.filter { it.isFile }?.map { file ->
            BufferedReader(InputStreamReader(file.inputStream())).use { it.readLine() }
        } ?: emptyList()
    }
}

