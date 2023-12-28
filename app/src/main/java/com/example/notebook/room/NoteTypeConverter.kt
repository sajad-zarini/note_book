package com.example.notebook.room

import androidx.room.TypeConverter
import com.example.notebook.models.NoteModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NoteTypeConverter {

    @TypeConverter
    fun toJson(notesModel: NoteModels): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<NoteModels?>() {}.type
        return gson.toJson(notesModel, type)
    }

    @TypeConverter
    fun toDataClass(note: String?): NoteModels? {
        if (note == null) {
            return null
        }

        val gson = Gson()
        val type: Type = object : TypeToken<NoteModels?>() {}.type
        return gson.fromJson(note, type)
    }
}