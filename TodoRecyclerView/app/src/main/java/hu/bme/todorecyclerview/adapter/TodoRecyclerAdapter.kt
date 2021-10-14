package hu.bme.todorecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.todorecyclerview.R
import hu.bme.todorecyclerview.data.Todo
import hu.bme.todorecyclerview.databinding.TodoRowBinding

class TodoRecyclerAdapter : RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    val context: Context
    var todoItems = mutableListOf<Todo>(
        Todo("Do the dishes", "2020. 10. 11.", false),
        Todo("Do the dishes", "2020. 10. 11.", false)
    )

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoRowBinding = TodoRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(todoRowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todoItems[holder.adapterPosition]
        holder.bind(currentTodo)

        holder.todoRowBinding.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }
    }

    fun addTodo(newTodo: Todo) {
        todoItems.add(newTodo)
        //notifyDataSetChanged()
        notifyItemInserted(todoItems.lastIndex)
    }

    fun deleteTodo(index: Int) {
        todoItems.removeAt(index)
        //notifyDataSetChanged()
        notifyItemRemoved(index)
    }

    inner class ViewHolder(val todoRowBinding: TodoRowBinding) : RecyclerView.ViewHolder(todoRowBinding.root) {
        fun bind(todo: Todo) {
            todoRowBinding.tvDate.text = todo.createDate
            todoRowBinding.cbDone.text = todo.title
            todoRowBinding.cbDone.isChecked = todo.done
        }
    }



}