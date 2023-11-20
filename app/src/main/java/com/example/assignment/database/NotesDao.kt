package com.example.assignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM NOTES")
    fun getAllNotes() : LiveData<List<Notes>>

    // INSERT
    @Insert
    suspend fun insertNotes(notes: Notes)

    // UPDATING

    @Update
    suspend fun updateNotes(notes: Notes)

}