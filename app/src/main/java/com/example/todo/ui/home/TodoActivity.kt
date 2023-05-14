package com.example.todo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.ActivityTodoBinding
import com.example.todo.ui.home.addTask.AddTaskBottomSheet
import com.example.todo.ui.home.addTask.OnDismissListener
import com.example.todo.ui.home.list.TodoListFragment
import com.example.todo.ui.home.settings.SettingsFragment

class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding
    var todoListFragment:TodoListFragment = TodoListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener  {
            when (it.itemId)
            {
                R.id.todos -> {
                    showFragment(todoListFragment)
                    binding.screenTitle.setText(R.string.todolist)
                }
                R.id.settings -> {
                    showFragment(SettingsFragment())
                    binding.screenTitle.setText(R.string.settings)
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.todos

        binding.fab.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet() {
        val addTaskBottomSheet:AddTaskBottomSheet = AddTaskBottomSheet()
        addTaskBottomSheet.onDismissListener= OnDismissListener {
            todoListFragment.loadTasksFromDataBase()
        }
        addTaskBottomSheet.show(supportFragmentManager,null)
    }

    private fun showFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container , fragment).commit()
    }
}