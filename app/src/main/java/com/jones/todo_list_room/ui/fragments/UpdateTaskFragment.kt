package com.jones.todo_list_room.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jones.todo_list_room.R
import com.jones.todo_list_room.ui.viewModels.UpdateTaskViewModel

class UpdateTaskFragment: BaseTaskFragment() {
    override val viewModel: UpdateTaskViewModel by viewModels {
        UpdateTaskViewModel.Factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDetails.text = getString(R.string.update_task)

        binding.mbSubmit.text = getString(R.string.update)

        binding.mbSubmit .setOnClickListener {
            viewModel.update()
        }
    }
}