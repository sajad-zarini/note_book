package com.example.notebook.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.models.NoteModels
import com.example.notebook.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    fun insertNoteToDatabase(noteModel: NoteModels) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertNote(noteModel)
        }
    }
}