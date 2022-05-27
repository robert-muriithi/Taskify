package com.roberts.tasksreminder.data.repository

import com.roberts.tasksreminder.data.local.TaskDatabase
import com.roberts.tasksreminder.data.local.TaskItem
import javax.inject.Inject

class TasksRepository @Inject constructor(private val database: TaskDatabase) {
    suspend fun insert(task: TaskItem) = database.getTasksDao().insert(task)
    suspend fun delete(task: TaskItem) = database.getTasksDao().delete(task)
    fun getAllTasks() = database.getTasksDao().getAllTasks()
}