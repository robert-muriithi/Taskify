package com.roberts.tasksreminder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.roberts.tasksreminder.R
import com.roberts.tasksreminder.databinding.ActivityMainBinding
import com.roberts.tasksreminder.databinding.FragmentCreateTaskBinding
import com.roberts.tasksreminder.ui.fragments.CreateTaskFragment
import com.roberts.tasksreminder.ui.fragments.other.AddTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addTask : AddTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked!", Toast.LENGTH_SHORT).show()

        }
    }
}