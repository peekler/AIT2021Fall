package hu.bme.todorecyclerview.touch

interface TodoTouchHelperCallback {
    fun onDismissed(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)
}