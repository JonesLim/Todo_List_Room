package com.jones.todo_list_room.ui.viewModels

import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repo: TasksRepo
) : ViewModel() {

//    val stateFlowTest: MutableStateFlow<String> = MutableStateFlow("Hello State Flow")
//    val sharedFlowTest: MutableSharedFlow<String> = MutableSharedFlow()

    fun getTasks(): Flow<List<Task>> {
        return repo.getTasks()
    }

//    fun addTask(task: Task) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.addTask(task)
//        }
//    } // for the testing task displayed.

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTask(task)
        }

//        viewModelScope.launch {
//            sharedFlowTest.emit("Hello Shared Flow")
//        }

    }

//    var counter: MutableStateFlow<Int> = MutableStateFlow(0)
//
//    fun increment() {
//        counter.value = counter.value + 1
//    }


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
