package com.route.islamic40gsunwed

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.route.islamic40gsunwed.fragments.HadeethFragment
import com.route.islamic40gsunwed.fragments.QuranFragment
import com.route.islamic40gsunwed.fragments.RadioFragment
import com.route.islamic40gsunwed.fragments.TasbeehFragment

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.islami_bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.navigation_quran -> QuranFragment()
                R.id.navigation_hadeeth -> HadeethFragment()
                R.id.navigation_tasbeeh -> TasbeehFragment()
                R.id.navigation_radio -> RadioFragment()
                else -> QuranFragment()
            }
            showFragment(fragment)
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.selectedItemId = R.id.navigation_quran
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.islami_fragment_container, fragment)
            .commit()
    }
}