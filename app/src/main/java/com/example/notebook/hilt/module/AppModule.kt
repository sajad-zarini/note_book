package com.example.notebook.hilt.module

import android.app.Application
import androidx.room.Room
import com.example.notebook.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "note_database")
            .build()
}