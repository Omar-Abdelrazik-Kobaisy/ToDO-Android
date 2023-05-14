package com.example.todo.ui.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.database.Mydatabase
import com.example.todo.databinding.FragmentListBinding

class TodoListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    lateinit var taskAdapter: TaskAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter= TaskAdapter(null)
        binding.tasksRecyclerview.adapter = taskAdapter
        loadTasksFromDataBase()
    }

    override fun onResume() {
        super.onResume()
        loadTasksFromDataBase()
    }

     fun loadTasksFromDataBase() {
         if(!isResumed)
         {
             return
         }
        val tasks = Mydatabase.getDataBase(requireActivity()).tasksDao().getAllTasks()
        taskAdapter.updateAdapter(tasks)
    }
}