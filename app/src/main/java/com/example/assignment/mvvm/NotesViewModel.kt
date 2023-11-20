package com.example.assignment.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.database.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {
    val inputTitle = MutableLiveData<String>()
    val inputSubtitle = MutableLiveData<String>()
    val inputNoteText = MutableLiveData<String>()
    
    val displayAllNotes = repository.allNotes()


    fun addNotes(){

        val title  = inputTitle.value
        val subtitle = inputSubtitle.value
        val text = inputNoteText.value
        insertNote(Notes(0, title!!, subtitle!!, text!!))

    }

    fun insertNote(note: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insertNote(note)

    }

    fun editUpdateNote(note: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.updateNotes(note)
    }


    fun editUpdate(id: Int, title: String, sub: String, text: String ){
        editUpdateNote(Notes(id, title, sub, text))

    }


}