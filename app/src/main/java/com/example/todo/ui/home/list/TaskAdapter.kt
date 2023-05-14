package com.example.todo.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemTaskBinding


class TaskAdapter(var tasks: List<Task>?):Adapter<TaskAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item  = tasks?.get(position)
        with(holder)
        {
            with(binding)
            {
                taskTitle.text=item?.title
                taskDesc.text =item?.description

            }
        }
    }

    override fun getItemCount(): Int = tasks?.size ?: 0

    fun updateAdapter(newTasks : List<Task>)
    {
        tasks = newTasks
        notifyDataSetChanged()
    }
}