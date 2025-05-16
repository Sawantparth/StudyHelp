package com.studycompanion.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studycompanion.app.data.model.StudyMaterial
import com.studycompanion.app.data.model.StudyProgress
import com.studycompanion.app.data.model.PracticeResult

@Database(
    entities = [
        StudyMaterial::class,
        StudyProgress::class,
        PracticeResult::class
    ],
    version = 1
)
abstract class StudyDatabase : RoomDatabase() {
    abstract fun studyMaterialDao(): StudyMaterialDao
    abstract fun studyProgressDao(): StudyProgressDao
    abstract fun practiceResultDao(): PracticeResultDao
}

@Dao
interface StudyMaterialDao {
    @Query("SELECT * FROM study_materials ORDER BY uploadDate DESC")
    suspend fun getAllMaterials(): List<StudyMaterial>

    @Insert
    suspend fun insertMaterial(material: StudyMaterial): Long

    @Query("SELECT * FROM study_materials WHERE id = :id")
    suspend fun getMaterialById(id: Long): StudyMaterial?
}

@Dao
interface StudyProgressDao {
    @Query("SELECT * FROM study_progress WHERE materialId = :materialId")
    suspend fun getProgressForMaterial(materialId: Long): List<StudyProgress>

    @Insert
    suspend fun insertProgress(progress: StudyProgress)

    @Query("UPDATE study_progress SET completionPercentage = :percentage WHERE id = :id")
    suspend fun updateProgress(id: Long, percentage: Float)
}

@Dao
interface PracticeResultDao {
    @Query("SELECT * FROM practice_results ORDER BY date DESC")
    suspend fun getAllResults(): List<PracticeResult>

    @Insert
    suspend fun insertResult(result: PracticeResult)

    @Query("SELECT AVG(score) FROM practice_results WHERE topic = :topic")
    suspend fun getAverageScoreForTopic(topic: String): Float?
}
