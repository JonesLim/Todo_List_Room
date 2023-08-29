package com.jones.todo_list_room.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jones.todo_list_room.data.model.Task
import com.jones.todo_list_room.databinding.FragmentHomeBinding
import com.jones.todo_list_room.ui.adapter.TasksAdapter
import com.jones.todo_list_room.ui.viewModels.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TasksAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()

        lifecycleScope.launch {
            viewModel.getTasks().collect {
                adapter.setTasks(it)
            }
        }

        binding.efabAdd.setOnClickListener {
//            val rand = (1..100000).random()
//            val title = "Title $rand"
//            val desc = "Description $rand"
//
//            viewModel.addTask(Task(title = title, desc = desc)) // to check if it works

            binding.efabAdd.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }

    fun setUpAdapter() {
        adapter = TasksAdapter(
            emptyList(),
        {
            viewModel.deleteTask(it)
        },
        {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateTaskFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}