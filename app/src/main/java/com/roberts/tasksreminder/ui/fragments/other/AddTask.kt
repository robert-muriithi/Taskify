package com.roberts.tasksreminder.ui.fragments.other

import com.roberts.tasksreminder.data.local.TaskItem

interface AddTask {
    fun saveTask(taskItem: TaskItem)
}