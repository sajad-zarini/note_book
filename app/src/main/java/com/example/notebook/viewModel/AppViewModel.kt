package com.example.notebook.viewModel

import androidx.lifecycle.ViewModel
import com.example.notebook.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
}