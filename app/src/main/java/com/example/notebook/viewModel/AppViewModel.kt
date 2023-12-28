package com.example.notebook.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.models.NoteModels
import com.example.notebook.repository.AppRepository
import com.example.notebook.room.entities.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    init {
        getAllDataFromDb()
    }

    private var data:MutableLiveData<List<NoteEntity>> = MutableLiveData()
    var liveData: LiveData<List<NoteEntity>> = data

    fun insertNoteToDatabase(noteModel: NoteModels) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertNote(noteModel)
        }
    }

    fun getAllDataFromDb() {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllData().collect {
                data.postValue(it)
            }
        }
    }
}