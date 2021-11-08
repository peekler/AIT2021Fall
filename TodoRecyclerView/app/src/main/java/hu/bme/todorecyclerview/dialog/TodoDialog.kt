package hu.bme.todorecyclerview.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.todorecyclerview.ScrollingActivity
import hu.bme.todorecyclerview.data.Todo
import hu.bme.todorecyclerview.databinding.TodoDialogBinding
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(newTodo: Todo)

        fun todoUpdated(editedTodo: Todo)
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

    var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())

        if (arguments != null && requireArguments().containsKey(
                ScrollingActivity.KEY_TODO_EDIT)) {
            isEditMode = true
            dialogBuilder.setTitle("Edit Todo")
        } else {
            isEditMode = false
            dialogBuilder.setTitle("New Todo")
        }

        todoDialogBinding = TodoDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(todoDialogBinding.root)

        //





        if (isEditMode) {
            val todoToEdit =
                requireArguments().getSerializable(
                    ScrollingActivity.KEY_TODO_EDIT) as Todo

            todoDialogBinding.etTodoText.setText(todoToEdit.title)
            todoDialogBinding.cbTodoDone.isChecked = todoToEdit.done
        }

        dialogBuilder.setPositiveButton("Create") {
            dialog, which ->
            // leave it empty
        }

        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }


    override fun onResume() {
        super.onResume()

        val dialog = dialog as AlertDialog
        val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)

        positiveButton.setOnClickListener {
            if (todoDialogBinding.etTodoText.text.isNotEmpty()) {
                if (isEditMode) {
                    handleTodoEdit()
                } else {
                    handleTodoCreate()
                }

                dialog.dismiss()
            } else {
                todoDialogBinding.etTodoText.error = "This field can not be empty"
            }
        }
    }

    private fun handleTodoCreate() {
        todoHandler.todoCreated(Todo(
            null,
            todoDialogBinding.etTodoText.text.toString(),
            Date(System.currentTimeMillis()).toString(),
            false
        ))
    }

    private fun handleTodoEdit() {
        val todoToEdit = (arguments?.getSerializable(
            ScrollingActivity.KEY_TODO_EDIT) as Todo).copy(
            title =  todoDialogBinding.etTodoText.text.toString(),
            done = todoDialogBinding.cbTodoDone.isChecked
        )

        todoHandler.todoUpdated(todoToEdit)
    }




}