package hu.bme.todorecyclerview.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.todorecyclerview.data.Todo
import hu.bme.todorecyclerview.databinding.TodoDialogBinding
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(newTodo: Todo)
    }

    lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TodoHandler){
            todoHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface.")
        }
    }

    lateinit var todoDialogBinding: TodoDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("New Todo")

        todoDialogBinding = TodoDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(todoDialogBinding.root)

        dialogBuilder.setPositiveButton("Create") {
            dialog, which ->

            val newTodo = Todo(null,
                todoDialogBinding.etTodoText.text.toString(),
                Date(System.currentTimeMillis()).toString(),
                todoDialogBinding.cbTodoDone.isChecked)
            todoHandler.todoCreated(newTodo)
        }

        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }


}