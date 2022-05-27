package com.roberts.tasksreminder.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roberts.tasksreminder.R
import com.roberts.tasksreminder.data.local.TaskItem
import com.roberts.tasksreminder.databinding.TaskCardBinding
import com.roberts.tasksreminder.ui.fragments.CreateTaskFragment
import com.roberts.tasksreminder.ui.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val viewModel: MainViewModel) :
    ListAdapter<TaskItem, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {
    private var taskList = mutableListOf<TaskItem>()

    var dateFormat = SimpleDateFormat("EE dd MMM yyyy", Locale.US)
    var inputDateFormat = SimpleDateFormat("dd-M-yyyy", Locale.US)
    var outputDateString: String? = null

    inner class TaskViewHolder(private var binding: TaskCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskItem) {

            binding.title.text = task.title
            binding.time.text = task.time
            binding.description.text = task.description
            binding.status.text = if (task.status) "COMPLETED" else "UPCOMING"

            try {
                val date: Date? = inputDateFormat.parse(task.date)
                outputDateString = dateFormat.format(date!!)

                val items1 = outputDateString!!.split(" ").toTypedArray()
                val day = items1[0]
                val dd = items1[1]
                val month = items1[2]

                binding.day.text = day
                binding.date.text = dd
                binding.month.text = month

            } catch (e: Exception) {
                e.printStackTrace()
            }

            binding.options.setOnClickListener(View.OnClickListener { view: View? ->
                showPopUpMenu(
                    view,
                    position
                )
            })

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
                        AlertDialog.Builder(view.context, R.style.Theme_TasksReminder_Dialog)
                    alertDialogBuilder.setTitle(R.string.delete_confirmation)
                        .setMessage(R.string.sureToDelete)
                        .setPositiveButton(R.string.yes) { dialog, which ->
                            viewModel.delete(task)
                        }
                        .setNegativeButton(R.string.no) { dialog, which -> dialog.cancel() }.show()
                }
                R.id.update -> {
                    val createTaskBottomSheetFragment =
                        CreateTaskFragment()
                    createTaskBottomSheetFragment.show(
                        view.context.applicationContext,
                        createTaskBottomSheetFragment.tag
                    )
                    Toast.makeText(view.context, "Updates", Toast.LENGTH_SHORT).show()
                }
                R.id.completed -> {
                    val completeAlertDialog =
                        AlertDialog.Builder(view.context, R.style.Theme_TasksReminder_Dialog)
                    completeAlertDialog

                        .setTitle(R.string.confirmation)
                        .setMessage(R.string.sureToMarkAsComplete)
                        .setPositiveButton(R.string.yes) { _, _ ->
                            viewModel.delete(task)
                            val dialog = Dialog(view.context)
                            dialog.setContentView(R.layout.dialog_completed_theme)
                            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            val button = dialog.findViewById<Button>(R.id.closeButton)
                            button.setOnClickListener {
                                dialog.dismiss()
                            }
                            dialog.show()
                        }
                        .setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }.show()
                }
            }
            false
        }
        popupMenu.show()
    }


}