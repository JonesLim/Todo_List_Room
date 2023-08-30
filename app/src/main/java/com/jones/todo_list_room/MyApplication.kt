package com.jones.todo_list_room

import android.app.Application
import androidx.room.Room
import com.jones.todo_list_room.data.TasksDatabase
import com.jones.todo_list_room.data.repo.TasksRepo

class MyApplication: Application() {
    lateinit var tasksRepo: TasksRepo

    override fun onCreate() {
        super.onCreate()

        val tasksDatabase = Room.databaseBuilder(
            this,
            TasksDatabase::class.java,
            TasksDatabase.name
        )
//            .fallbackToDestructiveMigration()
            .addMigrations(TasksDatabase.MIGRATION_1_2)
            .build()

        tasksRepo = TasksRepo(tasksDatabase.dao)
    }
}