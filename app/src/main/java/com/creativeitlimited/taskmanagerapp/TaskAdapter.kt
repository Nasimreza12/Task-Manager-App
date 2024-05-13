package com.creativeitlimited.taskmanagerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creativeitlimited.taskmanagerapp.database.User
import com.creativeitlimited.taskmanagerapp.databinding.ItemTaskBinding

class TaskAdapter(val listener:HandleUserClick, val taskItem:List<User>) :RecyclerView.Adapter<TaskViewHolder>(){

//    val listener:HandleUserClick
    interface HandleUserClick{
    fun onEditClick(user: User)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return taskItem.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskList=taskItem[position]
        with(holder.binding){
            tvTitle.text=taskList.title
            tvDescription.text=taskList.description
            tvDate.text=taskList.date
            tvTime.text=taskList.time
            }
        }
    }

class TaskViewHolder(var binding:ItemTaskBinding):RecyclerView.ViewHolder(binding.root)