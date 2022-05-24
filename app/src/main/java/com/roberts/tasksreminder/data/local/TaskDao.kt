package com.roberts.tasksreminder.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task : TaskItem)

    @Delete
    suspend fun delete (task: TaskItem)

    @Query("SELECT * FROM tasks_table")
    fun getAllTasks() : LiveData<List<TaskItem>>
}