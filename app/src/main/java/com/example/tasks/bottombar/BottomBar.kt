package com.example.tasks.bottombar

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tasks.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class bottomBar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_bar)

    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        val navController = findNavController(R.id.fragmentContainerView2)

        bottomNavigationView.setupWithNavController(navController)
    }


}