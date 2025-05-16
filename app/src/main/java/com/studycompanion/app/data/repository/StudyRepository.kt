package com.studycompanion.app.data.repository

import com.studycompanion.app.data.local.StudyMaterialDao
import com.studycompanion.app.data.local.StudyProgressDao
import com.studycompanion.app.data.local.PracticeResultDao
import com.studycompanion.app.data.model.StudyMaterial
import com.studycompanion.app.data.model.StudyProgress
import com.studycompanion.app.data.model.PracticeResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudyRepository @Inject constructor(
    private val materialDao: StudyMaterialDao,
    private val progressDao: StudyProgressDao,
    private val resultDao: PracticeResultDao
) {
    suspend fun uploadMaterial(title: String, content: String, type: String): Long {
        val material = StudyMaterial(
            title = title,
            content = content,
            type = type
        )
        return materialDao.insertMaterial(material)
    }

    suspend fun getAllMaterials(): List<StudyMaterial> =
        materialDao.getAllMaterials()

    suspend fun updateProgress(materialId: Long, topic: String, percentage: Float) {
        progressDao.insertProgress(
            StudyProgress(
                materialId = materialId,
                topic = topic,
                completionPercentage = percentage
            )
        )
    }

    suspend fun getMaterialProgress(materialId: Long): List<StudyProgress> =
        progressDao.getProgressForMaterial(materialId)

    suspend fun savePracticeResult(topic: String, score: Float, totalQuestions: Int) {
        resultDao.insertResult(
            PracticeResult(
                topic = topic,
                score = score,
                totalQuestions = totalQuestions
            )
        )
    }

    suspend fun getTopicPerformance(topic: String): Float =
        resultDao.getAverageScoreForTopic(topic) ?: 0f
}
