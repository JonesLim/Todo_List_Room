package com.jones.todo_list_room.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jones.todo_list_room.data.model.Task
import com.jones.todo_list_room.databinding.ItemLayoutTaskBinding

class TasksAdapter(
    private var tasks: List<Task>,
    private val onDeleteClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemLayoutTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = tasks.size


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.binding.task = task
        holder.binding.ivDelete.setOnClickListener {
            onDeleteClick(task)
        }
        holder.binding.llTask.setOnClickListener {
            onClick(task)
        }
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }
}