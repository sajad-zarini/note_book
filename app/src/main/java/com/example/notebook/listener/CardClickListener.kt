package com.example.notebook.listener

import com.example.notebook.room.entities.NoteEntity

interface CardClickListener {
    fun onItemClickListener(noteEntity: NoteEntity)
}