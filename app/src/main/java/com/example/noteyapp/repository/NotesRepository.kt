package com.example.noteyapp.repository

import androidx.lifecycle.LiveData
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NoteDao

// Repository: Serves as a single source of truth
// for data in your App, Handling all Data Ops:
// 1- Fetching data from the network
// 2- Loading Data from a local DB
class NotesRepository(private val notesDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        notesDao.insert(note)
    }
}

