package com.roberts.tasksreminder.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { TaskAdapter(viewModel) }

    @SuppressLint("NotifyDataSetChanged")
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
            /// binding.recyclerView.adapter = TaskAdapter()
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.completedTasks -> Toast.makeText(requireContext(), "$item", Toast.LENGTH_SHORT)
                .show()
            R.id.about -> Toast.makeText(requireContext(), "$item", Toast.LENGTH_SHORT).show()
            R.id.viewOnGithub -> Toast.makeText(requireContext(), "$item", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }


}