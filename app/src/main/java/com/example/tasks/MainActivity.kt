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
    private lateinit var btnFragment: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()

    }

    fun setupView(){
        btnLinearLayout = findViewById(R.id.btnTask1)
        btnFragment = findViewById(R.id.btnTask4)
    }

    fun setupListener(){
        btnLinearLayout.setOnClickListener {
            navigateToFirstTask()
        }

        btnFragment.setOnClickListener {
            navigateToFourthTask()
        }
    }

    fun navigateToFirstTask(){
        val intent = Intent(this@MainActivity, LinearLayout::class.java)
        startActivity(intent)
    }

    fun navigateToFourthTask(){
        val intent = Intent(this@MainActivity, FragmentSimpleTask::class.java)
        startActivity(intent)
    }
}