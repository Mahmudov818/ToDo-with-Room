package uz.mahmudovdev.todoapp.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import uz.mahmudovdev.todoapp.R
import uz.mahmudovdev.todoapp.data.AppDatabase
import uz.mahmudovdev.todoapp.data.TaskDao
import uz.mahmudovdev.todoapp.databinding.FragmentAddBinding
import uz.mahmudovdev.todoapp.model.TaskModel


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(binding.root.context)
        binding.calendar.setOnClickListener {
            showDatePickerDialog()
        }
        binding.hour.setOnClickListener {
            showTimePickerDialog()
        }
        binding.saveTask.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val taskModel = TaskModel(0, binding.taskName.toString(), binding.calendarTxt.toString())
        db.taskDao().insertData(taskModel)
    }

    @SuppressLint("SetTextI18n")
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(
            binding.root.context,
            { _, hourOfDay, minute ->
                if (minute < 10) {
                    binding.hourTxt.text = "$hourOfDay : 0$minute"
                } else {
                    binding.hourTxt.text = "$hourOfDay : $minute"
                }
            }, currentHour, currentMinute, true
        )
        timePicker.create()
        timePicker.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(
            binding.root.context,
            { _, year, month, dayOfMonth ->
                binding.calendarTxt.text = "$dayOfMonth / ${month + 1} / $year"
            }, year, month, day
        )
        datePicker.create()
        datePicker.show()

    }
}