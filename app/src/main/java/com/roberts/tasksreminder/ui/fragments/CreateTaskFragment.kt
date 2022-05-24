package com.roberts.tasksreminder.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roberts.tasksreminder.data.local.TaskItem
import com.roberts.tasksreminder.databinding.FragmentCreateTaskBinding
import com.roberts.tasksreminder.ui.fragments.other.AddTask
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CreateTaskFragment(private val listener : AddTask) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CreateTaskFragment"
    }

    var cal = Calendar.getInstance()
    private lateinit var binding: FragmentCreateTaskBinding

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addTask.setOnClickListener {
            val title = binding.addTaskTitle.text.toString()
            val desc = binding.addTaskDescription.text.toString()
            val date = binding.taskDate.text.toString()
            val time = binding.taskTime.text.toString()
            val event = binding.taskEvent.text.toString()

            when {
                title.isEmpty() -> binding.addTaskTitle.error = "Title required"
                desc.isEmpty() -> binding.addTaskDescription.error = "Add description"
                date.isEmpty() -> binding.taskDate.error = "Set task date"
                time.isEmpty() -> binding.taskTime.error = "Set task time"
                event.isEmpty() -> binding.taskEvent.error = "Describe the event"

                else -> {
                    val task = TaskItem(title, desc, date, time, event, false)
                    listener.saveTask(task)
                }
            }

        }
        /*binding.taskDate.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val calendar = Calendar.getInstance()
                val mYear = calendar.get(Calendar.YEAR)
                val mMonth = calendar.get(Calendar.MONTH)
                val mDay = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                        binding.taskDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        dismiss()
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
                datePickerDialog.show()
            }
            true
        }*/

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                //updateDateInView()
                val myFormat = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                //binding.taskDate.text = sdf.format(cal.time)
            }

        binding.taskDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })


        val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    //dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }
        return view
    }

}