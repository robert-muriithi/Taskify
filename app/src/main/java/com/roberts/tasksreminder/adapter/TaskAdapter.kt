package com.roberts.tasksreminder.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roberts.tasksreminder.R
import com.roberts.tasksreminder.data.local.TaskItem
import com.roberts.tasksreminder.databinding.TaskCardBinding
import com.roberts.tasksreminder.ui.fragments.CreateTaskFragment

class TaskAdapter () : ListAdapter<TaskItem, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {
    private var taskList = mutableListOf<TaskItem>()

    inner class TaskViewHolder(private var binding: TaskCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskItem) {
            binding.date.text = task.date
            binding.title.text = task.title
            binding.time.text = task.time
            binding.description.text = task.description
            binding.status.text = task.status.toString()

            binding.options.setOnClickListener {
                //showPopUpMenu(itemView)
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

    fun showPopUpMenu(view: View?, position: Int) {
        taskList = currentList as MutableList<TaskItem>
        val task: TaskItem = taskList[position]
        val popupMenu = PopupMenu(view!!.context, view)
        popupMenu.menuInflater.inflate(R.menu.options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.delete -> {
                    val alertDialogBuilder =
                        AlertDialog.Builder(view.context)
                    alertDialogBuilder.setTitle(R.string.delete_confirmation)
                        .setMessage(R.string.sureToDelete)
                        .setPositiveButton(R.string.yes) { dialog, which ->
                            /*deleteTaskFromId(
                                task.getTaskId(),
                                position
                            )*/
                        }
                        .setNegativeButton(R.string.no) { dialog, which -> dialog.cancel() }.show()
                }
                R.id.update -> {
                    val createTaskBottomSheetFragment =
                        CreateTaskFragment()
                    /*createTaskBottomSheetFragment.setTaskId(
                        task.getTaskId(),
                        true,
                        context,
                        context
                    )
                    createTaskBottomSheetFragment.show(
                        context.getSupportFragmentManager(),
                        createTaskBottomSheetFragment.getTag()
                    )*/
                }
                R.id.completed -> {
                    val completeAlertDialog =
                        AlertDialog.Builder(view.context)
                    completeAlertDialog.setTitle(R.string.confirmation)
                        .setMessage(R.string.sureToMarkAsComplete)
                        .setPositiveButton(R.string.yes) { dialog, which ->
                            /*showCompleteDialog(
                                task.getTaskId(),
                                position
                            )*/
                        }
                        .setNegativeButton(R.string.no) { dialog, which -> dialog.cancel() }.show()
                }
            }
            false
        }
        popupMenu.show()
    }

    /*fun showCompleteDialog(taskId: Int, position: Int) {
        val dialog1 = Dialog(context)
        val dialog = Dialog(view.context, R.style.AppTheme)
        dialog.setContentView(R.layout.dialog_completed_theme)
        val close = dialog.findViewById<Button>(R.id.closeButton)
        close.setOnClickListener { view: View? ->
            deleteTaskFromId(taskId, position)
            dialog.dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }*/




}