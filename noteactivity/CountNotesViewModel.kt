package com.example.noteactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.notesapp.NoteRepository


class CountViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val _noteCount = MutableLiveData<Int>()
    val noteCount: LiveData<Int> get() = _noteCount

    fun getNoteCount() {
        _noteCount.value = noteRepository.getNoteCount()
    }

    fun insertNote() {
        viewModelScope.launch {
            // Perform any necessary operations before inserting the note, such as retrieving data from UI fields
            val note = Note()
            noteRepository.insert(note)
            getNoteCount()
        }
    }
}
