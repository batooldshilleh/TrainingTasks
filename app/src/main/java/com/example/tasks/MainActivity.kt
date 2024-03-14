package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnLinearLayout: Button
    private lateinit var btnFragment: Button
    private lateinit var btnConstraintLayout: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()
    }

    private fun setupView() {
        btnLinearLayout = findViewById(R.id.btnTask1)
        btnFragment = findViewById(R.id.btnTask4)
        btnConstraintLayout = findViewById(R.id.btnTask2)
        btnLogin = findViewById(R.id.btnTask3)
    }

    private fun setupListener() {
        btnLinearLayout.setOnClickListener {
            navigateToFirstTask()
        }

        btnFragment.setOnClickListener {
            navigateToFourthTask()
        }

        btnLogin.setOnClickListener {
            navigateToThirdTask()
        }

        btnConstraintLayout.setOnClickListener {
            navigateToSecondTask()
        }
    }

    private fun navigateToFirstTask() {
        val intent = Intent(this@MainActivity, LinearLayout::class.java)
        startActivity(intent)
    }

    private fun navigateToFourthTask() {
        val intent = Intent(this@MainActivity, FragmentSimpleTask::class.java)
        startActivity(intent)
    }

    private fun navigateToThirdTask() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun navigateToSecondTask() {
        val intent = Intent(this@MainActivity, ConstraintLayout::class.java)
        startActivity(intent)
    }
}
