package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    private lateinit var typeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        showData()
        appBarListener()

    }

    fun showData(){
        typeTextView = findViewById(R.id.tvType)

        val userType = intent.getStringExtra("userType")
        typeTextView.text = userType
    }

    fun appBarListener(){
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            logout()
        }
    }
    private fun logout() {
        val sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}