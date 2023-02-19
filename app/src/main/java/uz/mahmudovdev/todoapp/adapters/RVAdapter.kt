package uz.mahmudovdev.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mahmudovdev.todoapp.databinding.LayoutRvItemBinding
import uz.mahmudovdev.todoapp.model.TaskModel

class RVAdapter() : RecyclerView.Adapter<RVAdapter.Vh>() {
    private val taskList = ArrayList<TaskModel>()

    inner class Vh(private val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: TaskModel) {
            binding.index.text = (layoutPosition + 1).toString()
            binding.taskName.text = task.taskName
            binding.date.text = task.taskDate
            binding.status.text = "Bajarilmoqda"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Vh(LayoutRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = taskList.size


    override fun onBindViewHolder(holder: Vh, position: Int) {
        val task = taskList[position]
        holder.onBind(task)
    }

    fun refreshData(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

}