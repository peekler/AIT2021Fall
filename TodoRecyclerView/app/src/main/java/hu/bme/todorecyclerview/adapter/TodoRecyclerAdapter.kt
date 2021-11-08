package hu.bme.todorecyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.todorecyclerview.R
import hu.bme.todorecyclerview.ScrollingActivity
import hu.bme.todorecyclerview.data.AppDatabase
import hu.bme.todorecyclerview.data.Todo
import hu.bme.todorecyclerview.databinding.TodoRowBinding
import hu.bme.todorecyclerview.touch.TodoTouchHelperCallback
import java.util.*
import kotlin.concurrent.thread

class TodoRecyclerAdapter : ListAdapter<Todo, TodoRecyclerAdapter.ViewHolder>, TodoTouchHelperCallback {

    val context: Context

    constructor(context: Context) : super(TodoDiffCallback()) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoRowBinding = TodoRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(todoRowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = getItem(holder.adapterPosition)
        holder.bind(currentTodo)

        holder.todoRowBinding.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }

        holder.todoRowBinding.btnEdit.setOnClickListener {
            // Edit...
            (context as ScrollingActivity).showEditDialog(currentTodo)
        }

        holder.todoRowBinding.cbDone.setOnClickListener {
            currentTodo.done = holder.todoRowBinding.cbDone.isChecked
            thread {
                AppDatabase.getInstance(context).todoDao().updateTodo(currentTodo)
            }
        }
    }


    fun deleteTodo(index: Int) {
        thread {
            AppDatabase.getInstance(context).todoDao().deleteTodo(getItem(index))
        }
    }

    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(val todoRowBinding: TodoRowBinding) : RecyclerView.ViewHolder(todoRowBinding.root) {
        fun bind(todo: Todo) {
            todoRowBinding.tvDate.text = todo.createDate
            todoRowBinding.cbDone.text = todo.title
            todoRowBinding.cbDone.isChecked = todo.done
        }
    }
}


class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem._id == newItem._id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}