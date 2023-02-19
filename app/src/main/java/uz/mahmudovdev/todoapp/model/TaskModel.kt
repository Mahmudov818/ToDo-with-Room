package uz.mahmudovdev.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class TaskModel {
    @PrimaryKey(autoGenerate = true)
    val index: Int

    @ColumnInfo(name = "name")
    val taskName: String

    @ColumnInfo(name = "date")
    val taskDate: String

    constructor(index: Int, taskName: String, taskDate: String) {
        this.index = index
        this.taskName = taskName
        this.taskDate = taskDate
    }
}
