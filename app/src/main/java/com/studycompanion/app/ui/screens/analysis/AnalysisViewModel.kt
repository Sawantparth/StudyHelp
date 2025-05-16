package com.studycompanion.app.ui.screens.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studycompanion.app.data.repository.StudyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val repository: StudyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalysisUiState>(AnalysisUiState.Loading)
    val uiState: StateFlow<AnalysisUiState> = _uiState.asStateFlow()

    // Cache for analysis data
    private var cachedAnalysis: AnalysisData? = null

    init {
        loadAnalysisData()
    }

    private fun loadAnalysisData() {
        viewModelScope.launch {
            try {
                _uiState.value = AnalysisUiState.Loading

                // Return cached data if available
                cachedAnalysis?.let {
                    _uiState.value = AnalysisUiState.Success(
                        topicImportance = it.topicImportance,
                        questionPatterns = it.questionPatterns,
                        recommendations = it.recommendations
                    )
                    return@launch
                }

                // Load and analyze study materials
                val materials = repository.getAllMaterials()
                val analysis = analyzeMaterials(materials)
                
                // Cache the results
                cachedAnalysis = analysis
                
                _uiState.value = AnalysisUiState.Success(
                    topicImportance = analysis.topicImportance,
                    questionPatterns = analysis.questionPatterns,
                    recommendations = analysis.recommendations
                )
            } catch (e: Exception) {
                _uiState.value = AnalysisUiState.Error(
                    "Failed to load analysis: ${e.message}"
                )
            }
        }
    }

    fun refreshAnalysis() {
        cachedAnalysis = null
        loadAnalysisData()
    }
}

sealed class AnalysisUiState {
    data object Loading : AnalysisUiState()
    data class Success(
        val topicImportance: List<TopicImportance>,
        val questionPatterns: List<QuestionPattern>,
        val recommendations: List<StudyRecommendation>
    ) : AnalysisUiState()
    data class Error(val message: String) : AnalysisUiState()
}

data class AnalysisData(
    val topicImportance: List<TopicImportance>,
    val questionPatterns: List<QuestionPattern>,
    val recommendations: List<StudyRecommendation>
)

data class TopicImportance(
    val topic: String,
    val score: Float,
    val examProbability: Float
)

data class QuestionPattern(
    val type: String,
    val frequency: Int,
    val averageMarks: Float
)

data class StudyRecommendation(
    val topic: String,
    val importanceScore: Float,
    val recommendedHours: Int,
    val keyPoints: List<String>
)
