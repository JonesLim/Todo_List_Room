package com.jones.todo_list_room.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.jones.todo_list_room.R
import com.jones.todo_list_room.ui.viewModels.AddTaskViewModel

class AddTaskFragment : BaseTaskFragment() {

    override val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mbSubmit.setOnClickListener {
            viewModel.addTask()
        }
    }

//    private lateinit var binding: FragmentAddTaskBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel
//
//        viewModel.finish.asLiveData().observe(viewLifecycleOwner) {
//            NavHostFragment.findNavController(this).popBackStack()
//        }
//
//        viewModel.error.asLiveData().observe(viewLifecycleOwner) {
//            // show error
//            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
//
//            snackbar.also {
//                it.setBackgroundTint(
//                    ContextCompat.getColor(requireContext(), R.color.red)
//                )
//            }.show()
//        }
//    }

}