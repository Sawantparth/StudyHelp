package com.studycompanion.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studycompanion.app.ui.screens.analysis.QuestionPattern
import com.studycompanion.app.ui.screens.analysis.TopicImportance
import com.studycompanion.app.ui.theme.Orange
import com.studycompanion.app.ui.theme.LightOrange
import com.studycompanion.app.ui.theme.White

@Composable
fun ChartContainer(
    title: String,
    data: Any,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))

            when (data) {
                is List<*> -> {
                    when (val firstItem = data.firstOrNull()) {
                        is TopicImportance -> TopicImportanceChart(data as List<TopicImportance>)
                        is QuestionPattern -> QuestionPatternChart(data as List<QuestionPattern>)
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Orange
        )
    }
}

@Composable
private fun TopicImportanceChart(
    data: List<TopicImportance>
) {
    Column {
        data.forEach { topic ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = topic.topic,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Score: ${topic.score}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Orange
                )
            }
        }
    }
}

@Composable
private fun QuestionPatternChart(
    data: List<QuestionPattern>
) {
    Column {
        data.forEach { pattern ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pattern.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Frequency: ${pattern.frequency}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Orange
                )
            }
        }
    }
}