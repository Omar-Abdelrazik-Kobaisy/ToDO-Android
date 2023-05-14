package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TasksDao
import com.example.todo.database.model.Task


@Database(entities = [Task::class] , version = 1, exportSchema = false)
abstract class Mydatabase :RoomDatabase(){
    // function return Dao
    abstract fun tasksDao():TasksDao



    companion object
    {
        private var mydatabase:Mydatabase ?=null
        fun getDataBase(context: Context):Mydatabase
        {
            if(mydatabase == null) {
                //initialize
                mydatabase = Room.databaseBuilder(
                    context,
                    Mydatabase::class.java,
                    "database-name"//file name of data base
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mydatabase!!
        }
    }

}