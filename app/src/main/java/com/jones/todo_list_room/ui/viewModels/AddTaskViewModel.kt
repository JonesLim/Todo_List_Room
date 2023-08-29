package com.jones.todo_list_room.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jones.todo_list_room.MyApplication
import com.jones.todo_list_room.data.model.Task
import com.jones.todo_list_room.data.repo.TasksRepo
import com.jones.todo_list_room.ui.utils.FieldAndReg
import com.jones.todo_list_room.ui.utils.Validation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repo: TasksRepo
) : BaseTaskViewModel() {

    fun addTask() {
        val task = validateAndGetTask()
        task?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repo.addTask(task)
                finish.emit(Unit)
            }
        }
    }
//    val title: MutableStateFlow<String> = MutableStateFlow("")
//    val desc: MutableStateFlow<String> = MutableStateFlow("")
//    val error: MutableSharedFlow<String> = MutableSharedFlow()
//    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
//
//    fun addTask() {
//        viewModelScope.launch(Dispatchers.IO) {
////            val task =  Task(title = title.value, desc = desc.value)
////            repo.addTask(task)
//
//            val result = Validation.validate(
//                FieldAndReg(
//                    name = "Title",
//                    value = title.value,
//                    reg = "[A-Za-z0-9 ]{3,20}"
//                ),
//                FieldAndReg(
//                    name = "Description",
//                    value = desc.value,
//                    reg = ".*[A-Za-z0-9]{5,}.*"
//                )
//            )
//            if (result.first) {
//                val task = Task(title = title.value, desc = desc.value)
//                repo.addTask(task)
//                finish.emit(Unit)
//            } else {
//                // show error
//                error.emit(result.second)
//            }
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).tasksRepo
                AddTaskViewModel(
                    repo = myRepository
                )
            }
        }
    }
}