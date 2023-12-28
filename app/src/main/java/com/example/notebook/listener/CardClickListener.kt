package com.example.notebook.listener

import android.view.View
import com.example.notebook.room.entities.NoteEntity

interface CardClickListener {
    fun onItemClickListener(noteEntity: NoteEntity)

    fun onMenuClickListener(imageFilterButton: View, noteEntity: NoteEntity)
}