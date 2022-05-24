package com.roberts.tasksreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TaskDao
}