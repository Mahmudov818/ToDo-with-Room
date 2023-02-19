package uz.mahmudovdev.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.mahmudovdev.todoapp.databinding.ActivityMainBinding
import uz.mahmudovdev.todoapp.ui.AddFragment
import uz.mahmudovdev.todoapp.ui.HomeFragment
import uz.mahmudovdev.todoapp.ui.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> loadFragment(HomeFragment())
                R.id.settingsFragment -> loadFragment(SettingsFragment())
                R.id.addFragment -> loadFragment(AddFragment())
                else -> {false}
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view_tag, fragment)
        transaction.commit()
        return true
    }

}