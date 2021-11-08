package hu.bme.todorecyclerview.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todoTable")
data class Todo(
    @PrimaryKey(autoGenerate = true) var _id: Long?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "createDate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean) : Serializable