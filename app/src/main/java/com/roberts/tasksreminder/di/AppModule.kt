package com.roberts.tasksreminder.di

import android.app.Application
import androidx.room.Room
import com.roberts.tasksreminder.data.local.TaskDatabase
import com.roberts.tasksreminder.data.repository.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): TaskDatabase =
        Room.databaseBuilder(application, TaskDatabase::class.java, "tasksDb.db")
            .build()

    @Provides
    @Singleton
    fun provideRepository(database: TaskDatabase): TasksRepository {
        return TasksRepository(database)
    }

}