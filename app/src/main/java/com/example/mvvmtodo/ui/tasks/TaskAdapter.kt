package com.example.mvvmtodo.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodo.data.Task
import com.example.mvvmtodo.databinding.ItemTaskBinding



class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskViewHolder.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                checkbox.isChecked = task.completed
                textview.text= task.name
                textview.paint.isStrikeThruText = task.completed
                priorityLabel.isVisible=task.important

            }
        }

        //detect changes between items
        class DiffCallback : DiffUtil.ItemCallback<Task>(){

            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem

        }
    }
}