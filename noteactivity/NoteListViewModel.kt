package com.example.noteactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    fun getAllNotes(): LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }
}

// CountNotesViewModel.kt
class CountNotesViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val _noteCount = MutableLiveData<Int>()
    val noteCount: LiveData<Int> get() = _noteCount

    fun getNoteCount() {
        _noteCount.value = noteRepository.getNoteCount().value
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insert(note)
            getNoteCount()
        }
    }
}