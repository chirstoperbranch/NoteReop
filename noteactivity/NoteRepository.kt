package com.example.noteactivity

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NoteRepository {
    fun getAllNotes(): LiveData<List<Note>>
    fun getNoteCount(): LiveData<Int>
    suspend fun insert(note: Note)
}

// NoteRepositoryImpl.kt
class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun getNoteCount(): LiveData<Int> {
        return noteDao.getNoteCount()
    }

    override suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }
}