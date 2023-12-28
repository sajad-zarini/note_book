package com.example.notebook.repository

import com.example.notebook.models.NoteModels
import com.example.notebook.room.AppDatabase
import com.example.notebook.room.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    appDatabase: AppDatabase
) {
    private val roomDao = appDatabase.roomDao()

    fun insertNote(noteModel: NoteModels) {
        val noteEntity = NoteEntity(0, noteModel)
        roomDao.insert(noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) {
        roomDao.update(noteEntity)
    }

    fun getAllData(): Flow<List<NoteEntity>> {
        return roomDao.getAll()
    }
}