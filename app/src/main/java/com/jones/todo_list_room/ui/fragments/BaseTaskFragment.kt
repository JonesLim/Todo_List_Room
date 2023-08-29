package com.jones.todo_list_room.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.jones.todo_list_room.R
import com.jones.todo_list_room.databinding.FragmentAddUpdateTaskBinding
import com.jones.todo_list_room.ui.viewModels.BaseTaskViewModel

abstract class BaseTaskFragment : Fragment() {

    abstract val viewModel: BaseTaskViewModel
    protected lateinit var binding: FragmentAddUpdateTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUpdateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.finish.asLiveData().observe(viewLifecycleOwner) {
            NavHostFragment.findNavController(this).popBackStack()
        }

        viewModel.error.asLiveData().observe(viewLifecycleOwner) {
            // show error
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)

            snackbar.also {
                it.setBackgroundTint(
                    ContextCompat.getColor(requireContext(), R.color.red)
                )
            }.show()
        }
    }
}