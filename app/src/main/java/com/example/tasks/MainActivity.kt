package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnLinearLayout: Button
    private lateinit var btnConstarintLayout: Button
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()

    }

    fun setupView(){
        btnLinearLayout = findViewById(R.id.btnTask1)
        btnConstarintLayout = findViewById(R.id.btnTask2)
        btnLogin = findViewById(R.id.btnTask3)
    }

    fun setupListener(){
        btnLinearLayout.setOnClickListener {
            navigateToFirstTask()
        }
        btnConstarintLayout.setOnClickListener {  }
        btnLogin.setOnClickListener {
            navigateToTheardTask()
        }
    }

    fun navigateToFirstTask(){
        val intent = Intent(this@MainActivity, LinearLayout::class.java)
        startActivity(intent)
    }

    fun navigateToTheardTask(){
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }
}