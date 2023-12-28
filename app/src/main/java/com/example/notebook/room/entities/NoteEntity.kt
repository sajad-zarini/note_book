package com.example.notebook.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notebook.models.NoteModels

@Entity(
    tableName = "notes"
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var uid:Int,
    val noteModels: NoteModels
)
