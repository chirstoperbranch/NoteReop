package com.example.noteactivity

import android.app.Application
import androidx.room.Room

class NotesApplication : Application() {
    val noteRepository: NoteRepository by lazy {
        val database = Room.databaseBuilder(this, NoteDatabase::class.java, "note-db")
            .build()
        NoteRepositoryImpl(database.noteDao())
    }
}