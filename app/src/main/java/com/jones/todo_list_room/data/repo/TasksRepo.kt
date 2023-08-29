package com.jones.todo_list_room.data.repo

import com.jones.todo_list_room.data.TasksDao
import com.jones.todo_list_room.data.model.Task
import kotlinx.coroutines.flow.Flow

class TasksRepo(
    private val dao: TasksDao
) {
    fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    fun addTask(task: Task) {
       dao.insertTask(task)
    }

    fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }
}