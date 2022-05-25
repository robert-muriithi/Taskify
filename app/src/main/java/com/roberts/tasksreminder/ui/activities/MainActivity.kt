package com.roberts.tasksreminder.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.roberts.tasksreminder.adapter.TaskAdapter
import com.roberts.tasksreminder.databinding.ActivityMainBinding
import com.roberts.tasksreminder.ui.fragments.CreateTaskFragment
import com.roberts.tasksreminder.ui.fragments.other.AddTask
import com.roberts.tasksreminder.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /*private val viewmodel : MainViewModel by viewModels()*/
    private lateinit var binding: ActivityMainBinding
    /*private lateinit var addTask : AddTask
    private val adapter by lazy { TaskAdapter(viewmodel) }*/

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked!", Toast.LENGTH_SHORT).show()

        }

        binding.fab.setOnClickListener { view ->
            val createTaskBottomSheetFragment =
                CreateTaskFragment(addTask)
          //  createTaskBottomSheetFragment.setTaskId(0, false, this, this@MainActivity)
            createTaskBottomSheetFragment.show(
                supportFragmentManager,
                createTaskBottomSheetFragment.getTag()
            )
        }

        viewmodel.getAllTasks().observe(this, Observer{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })*/
    }
}