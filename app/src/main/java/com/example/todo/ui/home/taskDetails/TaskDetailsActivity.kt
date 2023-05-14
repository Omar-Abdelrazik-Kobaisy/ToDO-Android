package com.example.todo.ui.home.taskDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R
import com.example.todo.databinding.ActivityTaskDetailsBinding

class TaskDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityTaskDetailsBinding
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}