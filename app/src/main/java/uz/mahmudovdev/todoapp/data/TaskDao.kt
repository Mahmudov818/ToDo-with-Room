package uz.mahmudovdev.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.mahmudovdev.todoapp.model.TaskModel


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<TaskModel>>

    @Insert
    fun insertData(task: TaskModel)

    @Delete
    fun delete(task: TaskModel)
}