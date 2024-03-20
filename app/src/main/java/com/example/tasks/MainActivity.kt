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

    private lateinit var btnConstraintLayout: Button

    private lateinit var btnLogin: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()

    }

    fun setupView() {
        btnLinearLayout = findViewById(R.id.btnTask1)


        btnFragment = findViewById(R.id.btnTask4)

        btnConstraintLayout = findViewById(R.id.btnTask2)

        btnLogin = findViewById(R.id.btnTask3)




    }

    fun setupListener() {
        btnLinearLayout.setOnClickListener {
            navigate("com.example.tasks.linearlayout.LinearLayout")
        }

        btnFragment.setOnClickListener {
            navigate("com.example.tasks.fragmentapp.FragmentSimpleTask")
        }

        btnLogin.setOnClickListener {
            navigate("com.example.tasks.login.Login")
        }

        btnConstraintLayout.setOnClickListener {
            navigate("com.example.tasks.constrainlayout.ConstraintLayout")
        }

    }


    fun navigate(className: String) {

        val intent = Intent(this@MainActivity, Class.forName(className))
        startActivity(intent)

    }


}