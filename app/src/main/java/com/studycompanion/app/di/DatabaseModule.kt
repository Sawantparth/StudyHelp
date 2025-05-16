package com.studycompanion.app.di

import android.content.Context
import androidx.room.Room
import com.studycompanion.app.data.local.StudyDatabase
import com.studycompanion.app.data.local.StudyMaterialDao
import com.studycompanion.app.data.local.StudyProgressDao
import com.studycompanion.app.data.local.PracticeResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): StudyDatabase = Room.databaseBuilder(
        context,
        StudyDatabase::class.java,
        "study_companion.db"
    ).build()

    @Provides
    fun provideMaterialDao(database: StudyDatabase): StudyMaterialDao =
        database.studyMaterialDao()

    @Provides
    fun provideProgressDao(database: StudyDatabase): StudyProgressDao =
        database.studyProgressDao()

    @Provides
    fun provideResultDao(database: StudyDatabase): PracticeResultDao =
        database.practiceResultDao()
}
