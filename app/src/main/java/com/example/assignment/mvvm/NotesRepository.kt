package com.example.assignment.mvvm

import androidx.lifecycle.LiveData
import com.example.assignment.database.Notes
import com.example.assignment.database.NotesDao


//database->dao(work on database)->we store through methods of dao of our repository and we attach that data with our views in viewModel
class NotesRepository(val dao: NotesDao) {
    // display all notes
    fun allNotes() : LiveData<List<Notes>> {
        return dao.getAllNotes()
    }
    // insert note
    suspend fun insertNote(notes: Notes){
        dao.insertNotes(notes)
    }
    // update note
    suspend fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}