package com.example.notebook.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notebook.room.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<NoteEntity>>

    @Insert
    fun insert(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)
}