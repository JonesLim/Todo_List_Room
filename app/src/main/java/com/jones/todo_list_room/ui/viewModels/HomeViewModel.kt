package com.jones.todo_list_room.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jones.todo_list_room.MyApplication
import com.jones.todo_list_room.data.model.Task
import com.jones.todo_list_room.data.repo.TasksRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repo: TasksRepo
) : ViewModel() {
    fun getTasks(): Flow<List<Task>> {
        return repo.getTasks()
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTask(task)
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApplication).tasksRepo
                HomeViewModel(
                    repo = myRepository,
                )
            }
        }
    }
}

//    val tasks: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())

//
//    viewModelScope.launch {
//    repo.getTasks().collect {
//        tasks.value = it
//    }
//    }
