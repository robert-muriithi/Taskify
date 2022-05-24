package com.roberts.tasksreminder.adapter

import androidx.recyclerview.widget.DiffUtil
import com.roberts.tasksreminder.data.local.TaskItem

object TaskDiffUtil : DiffUtil.ItemCallback<TaskItem>() {
    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
    }

}
