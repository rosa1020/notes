package com.example.notes.data

import com.example.notes.data.db.Note
import com.example.notes.data.db.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

}