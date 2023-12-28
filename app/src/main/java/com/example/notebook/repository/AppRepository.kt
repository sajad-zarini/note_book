package com.example.notebook.repository

import com.example.notebook.room.AppDatabase
import javax.inject.Inject

class AppRepository @Inject constructor(
    appDatabase: AppDatabase
) {
    private val roomDao = appDatabase.roomDao()


}