package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    private lateinit var btnMove: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnMove = findViewById(R.id.myButton)

        btnMove.setOnClickListener {
            navigateToActivity1()
        }

    }

    private inline fun navigateToActivity1() {
        val intent = Intent(this@MainActivity2, MainActivity::class.java)
        startActivity(intent)
    }
}