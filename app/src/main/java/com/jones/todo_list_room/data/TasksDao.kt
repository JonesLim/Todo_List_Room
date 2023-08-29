package com.jones.todo_list_room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jones.todo_list_room.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Task?
}