package uz.mahmudovdev.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import uz.mahmudovdev.todoapp.R
import uz.mahmudovdev.todoapp.adapters.RVAdapter
import uz.mahmudovdev.todoapp.data.AppDatabase
import uz.mahmudovdev.todoapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var db: AppDatabase
    lateinit var adapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(binding.root.context)
        initRV()

        val allTasks = db.taskDao().getAllTasks()
        allTasks.observe(viewLifecycleOwner) { taskList ->
            adapter.refreshData(taskList)
        }

    }

    private fun initRV() {
        adapter = RVAdapter()
        binding.rv.adapter = adapter
    }


}