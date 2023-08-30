package com.jones.todo_list_room.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
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

//    private val homeFragmentState = HomeFragmentState()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val counter = savedInstanceState?.getInt("counter") ?: 0
//        homeFragmentState.counter.value = counter

        setUpAdapter()

        lifecycleScope.launch {
            viewModel.getTasks().collect {
                adapter.setTasks(it)
            }
        }

//        binding.efabAdd.setOnClickListener {
//            val rand = (1..100000).random()
//            val title = "Title $rand"
//            val desc = "Description $rand"
//
//            viewModel.addTask(Task(title = title, desc = desc)) // to check if it works
//          }

        binding.efabAdd.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
                NavHostFragment.findNavController(this).navigate(action)

//            homeFragmentState.increment()
//            viewModel.increment()

        }

//            viewModel.stateFlowTest.asLiveData().observe(viewLifecycleOwner) {
//                Log.d("State Flow Test:", it)
//            }
//            viewModel.sharedFlowTest.asLiveData().observe(viewLifecycleOwner) {
//                Log.d("State Flow Test:", it)
//            }

//        homeFragmentState.counter.asLiveData().observe(viewLifecycleOwner) {
//            Log.d("helloflow", "Value of counter: $it")
//        } // this will be destroyed when the fragment is destroyed.
//
//        viewModel.counter.asLiveData().observe(viewLifecycleOwner) {
//            Log.d("helloflow", "Counter from ViewModel: $it")
//        } // this will not be destroyed.

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

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("counter", homeFragmentState.counter.value)
//    } // to keep the value of counter.

}