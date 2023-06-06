package com.example.porter01.mainUi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.porter01.R
import com.example.porter01.databinding.ActivityMainBinding
import com.example.porter01.mainUi.home.HomeFragment
import com.example.porter01.mainUi.home.account.ProfileFragment
import com.example.porter01.mainUi.orders.OrdersFragment

private const val MY_FRAGMENT = "myFragment"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.order -> {
                    loadFragment(OrdersFragment())
                    true
                }
                R.id.account -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> true
            }
        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.activity_main, fragment)
        }
    }
}