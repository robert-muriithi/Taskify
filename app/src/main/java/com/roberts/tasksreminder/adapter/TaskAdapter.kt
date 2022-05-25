package com.roberts.tasksreminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roberts.tasksreminder.data.local.TaskItem
import com.roberts.tasksreminder.databinding.TaskCardBinding
import com.roberts.tasksreminder.ui.viewmodels.MainViewModel

class TaskAdapter (private  val viewModel: MainViewModel): ListAdapter<TaskItem, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {
    inner class TaskViewHolder(private var binding: TaskCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskItem) {
            binding.date.text = task.date
            binding.title.text = task.title
            binding.time.text = task.time
            binding.description.text = task.description
            binding.status.text = task.status.toString()

            binding.options.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            TaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }
}