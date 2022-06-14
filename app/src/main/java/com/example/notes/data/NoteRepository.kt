package com.example.notes.data

import com.example.notes.data.db.Note
import com.example.notes.data.db.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    suspend fun getNote(id: Int): Note? {
        return noteDao.getNote(id)
    }

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun removeNote(note: Note) {
        noteDao.delete(note)
    }

}