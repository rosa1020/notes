package com.example.notes.data.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)
}