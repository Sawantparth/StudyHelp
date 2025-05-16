package com.studycompanion.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_materials")
data class StudyMaterial(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val type: String,
    val uploadDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "study_progress")
data class StudyProgress(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val materialId: Long,
    val topic: String,
    val completionPercentage: Float,
    val lastStudyDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "practice_results")
data class PracticeResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val topic: String,
    val score: Float,
    val totalQuestions: Int,
    val date: Long = System.currentTimeMillis()
)
