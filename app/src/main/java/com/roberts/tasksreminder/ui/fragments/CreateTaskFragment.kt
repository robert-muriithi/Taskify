package com.roberts.tasksreminder.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roberts.tasksreminder.data.local.TaskItem
import com.roberts.tasksreminder.databinding.FragmentCreateTaskBinding
import com.roberts.tasksreminder.ui.fragments.other.ToEditable
import com.roberts.tasksreminder.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class CreateTaskFragment : BottomSheetDialogFragment(), ToEditable {

    companion object {
        private const val TAG = "CreateTaskFragment"
    }

    private val status = "UPCOMING"
    var cal = Calendar.getInstance()
    private lateinit var binding: FragmentCreateTaskBinding
    private val viewModel: MainViewModel by viewModels()

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
                    val task = TaskItem(title, desc, date, time, event, status.toBoolean())
                    viewModel.insert(task)
                    dismiss()
                    Log.d(TAG, "onCreateView: $task")
                }
            }

        }
        binding.taskDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val myFormat = "MM/dd/yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    binding.taskDate.text = sdf.format(cal.time).toEditable()
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)

            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()

        }

        binding.taskTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    val myFormat = "HH:mm"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    if (cal.get(Calendar.AM_PM) == Calendar.AM) {
                        binding.taskTime.text = sdf.format(cal.time).toEditable()
                    } else {
                        binding.taskTime.text = "${sdf.format(cal.time)} PM".toEditable()
                    }
                    binding.taskTime.text = sdf.format(cal.time).toEditable()
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()

            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)


        }
        val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }

        return view
    }

    override fun show(applicationContext: Context?, tag: String?) {

    }

}




