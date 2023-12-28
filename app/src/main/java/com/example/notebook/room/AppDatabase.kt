package com.example.notebook.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notebook.room.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}