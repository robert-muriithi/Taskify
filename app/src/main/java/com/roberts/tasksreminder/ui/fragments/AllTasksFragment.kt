package com.roberts.tasksreminder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.roberts.tasksreminder.R
import com.roberts.tasksreminder.adapter.TaskAdapter
import com.roberts.tasksreminder.databinding.FragmentAllTasksBinding
import com.roberts.tasksreminder.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTasksFragment : Fragment() {
    private lateinit var binding: FragmentAllTasksBinding
    private val viewModel : MainViewModel by viewModels()
    private val adapter by lazy { TaskAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_allTasksFragment_to_createTaskFragment)
        }

        viewModel.getAllTasks().observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = TaskAdapter()
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
        return view
    }

}