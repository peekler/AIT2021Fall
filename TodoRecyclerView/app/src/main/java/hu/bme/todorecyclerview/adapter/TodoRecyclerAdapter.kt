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
        val todoRowView = LayoutInflater.from(context).inflate(R.layout.todo_row, parent, false)
        return ViewHolder(todoRowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todoItems[position]
        holder.tvDate.text = currentTodo.createDate
        holder.cbDone.text = currentTodo.title
        holder.cbDone.isChecked = currentTodo.done
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        var cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
    }

}