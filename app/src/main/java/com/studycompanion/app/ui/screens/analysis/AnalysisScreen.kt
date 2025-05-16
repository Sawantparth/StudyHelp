package com.studycompanion.app.ui.screens.analysis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.studycompanion.app.ui.components.ChartContainer
import com.studycompanion.app.ui.components.LoadingIndicator
import com.studycompanion.app.ui.theme.Orange
import com.studycompanion.app.ui.theme.White

@Composable
fun AnalysisScreen(
    navController: NavController,
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        when (val state = uiState) {
            is AnalysisUiState.Loading -> {
                LoadingIndicator()
            }
            is AnalysisUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        Text(
                            text = "Study Material Analysis",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Topic Importance Chart
                    item {
                        ChartContainer(
                            title = "Topic Importance",
                            data = state.topicImportance
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Question Pattern Distribution
                    item {
                        ChartContainer(
                            title = "Question Patterns",
                            data = state.questionPatterns
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Study Recommendations
                    items(state.recommendations) { recommendation ->
                        RecommendationCard(recommendation)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            is AnalysisUiState.Error -> {
                ErrorMessage(message = state.message)
            }
        }
    }
}

@Composable
private fun RecommendationCard(recommendation: StudyRecommendation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recommendation.topic,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Importance Score: ${recommendation.importanceScore}",
                style = MaterialTheme.typography.bodyMedium,
                color = Orange
            )
            Text(
                text = "Recommended Hours: ${recommendation.recommendedHours}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}