package hu.bme.todorecyclerview

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hu.bme.todorecyclerview.adapter.TodoRecyclerAdapter
import hu.bme.todorecyclerview.data.AppDatabase
import hu.bme.todorecyclerview.data.Todo
import hu.bme.todorecyclerview.databinding.ActivityScrollingBinding
import hu.bme.todorecyclerview.dialog.TodoDialog
import hu.bme.todorecyclerview.touch.TodoRecyclerTouchCallback
import kotlin.concurrent.thread

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: TodoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            TodoDialog().show(supportFragmentManager, "TODO_DIALOG")
        }

        initTodoRecyclerView()
    }

    private fun initTodoRecyclerView() {
        adapter = TodoRecyclerAdapter(this)
        binding.recylerTodo.adapter = adapter

        var liveDataTodos = AppDatabase.getInstance(this).todoDao().getAllTodo()
        liveDataTodos.observe(this, Observer { items ->
            adapter.submitList(items)
        })

        val touchCallbakList = TodoRecyclerTouchCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbakList)
        itemTouchHelper.attachToRecyclerView(binding.recylerTodo)
    }



    override fun todoCreated(newTodo: Todo) {
        thread {
            AppDatabase.getInstance(this).todoDao().addTodo(newTodo)
        }
    }
}