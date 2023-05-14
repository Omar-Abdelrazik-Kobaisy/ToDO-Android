package com.example.todo.database.dao

import androidx.room.*
import com.example.todo.database.model.Task


@Dao
interface TasksDao {
    @Insert
    fun insertTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
    @Update
    fun updateTask(task: Task)
    @Query("SELECT * FROM Task")
    fun getAllTasks():List<Task>
}