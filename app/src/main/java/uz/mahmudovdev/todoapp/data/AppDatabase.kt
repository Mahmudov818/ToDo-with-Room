package uz.mahmudovdev.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.mahmudovdev.todoapp.model.TaskModel

@Database(entities = [TaskModel::class], version=1, exportSchema = true)
abstract class AppDatabase:RoomDatabase()  {
    abstract fun taskDao(): TaskDao
companion object{
    var instance: AppDatabase? = null
    private val LOCK = Any()
    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
        instance ?: buildDatabase(context).also {
            instance = it
        }
    }
    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "task").allowMainThreadQueries()
            .build()
}
}