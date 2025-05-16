package com.studycompanion.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studycompanion.app.data.model.StudyMaterial
import com.studycompanion.app.data.repository.StudyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StudyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMaterials()
    }

    private fun loadMaterials() {
        viewModelScope.launch {
            try {
                val materials = repository.getAllMaterials()
                _uiState.value = HomeUiState.Success(materials)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load materials: ${e.message}")
            }
        }
    }

    fun uploadMaterial(title: String, content: String, type: String) {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState.Loading
                repository.uploadMaterial(title, content, type)
                loadMaterials()
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to upload material: ${e.message}")
            }
        }
    }
}

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val materials: List<StudyMaterial>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
