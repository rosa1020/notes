package com.example.notes.data

import com.example.notes.data.db.Note
import com.example.notes.data.db.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun getNotes(): List<Note> = withContext(Dispatchers.IO) {
        return@withContext noteDao.getNotes()
    }

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun removeNote(note: Note) {
        noteDao.delete(note)
    }

}