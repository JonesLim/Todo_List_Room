package com.jones.todo_list_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jones.todo_list_room.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {

    abstract val dao: TasksDao

    companion object {
        const val name = "task_database"
    }
}