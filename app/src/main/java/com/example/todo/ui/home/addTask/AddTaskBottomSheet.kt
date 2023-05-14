package com.example.todo.ui.home.addTask

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo.database.Mydatabase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.ui.home.list.TodoListFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import kotlin.math.log

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskDate.text = ""+current_date.get(Calendar.DAY_OF_MONTH)+"/"+current_date.get(Calendar.MONTH)+"/"+current_date.get(Calendar.YEAR)
        binding.taskDate.setOnClickListener {
            showDatePicker()
        }
        binding.submit.setOnClickListener {
            addTask()
        }
    }

    var onDismissListener:OnDismissListener? = null

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss()

    }

    private fun addTask() {
//        Log.w("---------------------->", "addTask: clicked" )

        val title = binding.taskTitle.editText?.text.toString()
        val desc = binding.taskDesc.editText?.text.toString()
        if(title.isNullOrBlank())
        {
            binding.taskTitle.error = "please enter Task title"
        }else
        {
            binding.taskTitle.error = null
        }

        Mydatabase.getDataBase(requireActivity()).tasksDao().insertTask(Task(
            title = title ,
            description = desc,
            date = current_date.timeInMillis))


        showTaskInsertedDialog()
    }

    private fun showTaskInsertedDialog() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
            .setMessage("Task inserted successfully")
            .setPositiveButton("OK",object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                    dismiss()
                }
            }).setCancelable(false)

        alertDialogBuilder.show()
    }

    var current_date : Calendar = Calendar.getInstance() //return obj of Calender
    private fun showDatePicker() {
        val datePicker = DatePickerDialog(requireActivity(),DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            current_date.set(Calendar.YEAR,year)
            current_date.set(Calendar.MONTH,month+1)
            current_date.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            binding.taskDate.text = ""+current_date.get(Calendar.DAY_OF_MONTH)+"/"+current_date.get(Calendar.MONTH)+"/"+current_date.get(Calendar.YEAR)

        }, current_date.get(Calendar.YEAR),current_date.get(Calendar.MONTH),current_date.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

}