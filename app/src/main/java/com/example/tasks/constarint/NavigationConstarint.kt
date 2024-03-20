package com.example.tasks.constarint

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.R
import com.example.tasks.constrainlayout.ConstraintLayout

class NavigationConstarint : AppCompatActivity() {
    private lateinit var btnMove: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_constarint)
        btnMove = findViewById(R.id.myButton)

        btnMove.setOnClickListener {
            navigateToActivity1()
        }

    }

    private inline fun navigateToActivity1() {
        val intent = Intent(this@NavigationConstarint, ConstraintLayout::class.java)
        startActivity(intent)
    }
}