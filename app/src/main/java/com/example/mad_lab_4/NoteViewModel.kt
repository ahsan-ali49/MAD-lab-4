package com.example.mad_lab_4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "notes").build()
    private val noteDao = db.noteDao()

    val notes = noteDao.getAllNotes().asLiveData()  // Change this if it's LiveData


    fun addNewNote(content: String) {
        viewModelScope.launch {
            noteDao.insert(Note(content = content))
        }
    }
}
