package com.example.fragmentapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView


class MainActivity : AppCompatActivity() {
    private lateinit var btnFirstFragment: Button
    private lateinit var btnSecondFragment: Button
    private lateinit var  fragmentContener: FragmentContainerView

    val frag1 = FirstFragment()
    val frag2 = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViewss()
        defaultFragment()
        setupClickListeners()

        }

    fun initViewss(){
        btnFirstFragment = findViewById(R.id.btnFragment1)
        btnSecondFragment = findViewById(R.id.btn2)
        fragmentContener = findViewById(R.id.fcvFragment1)
    }

    fun defaultFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(fragmentContener.id,frag1)
            commit()
        }
    }

    fun setupClickListeners(){
        btnFirstFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {

                replace(R.id.fcvFragment1, frag1)
                addToBackStack("first_fragment")
                setReorderingAllowed(true)
                commit()

            }
        }

        btnSecondFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcvFragment1, frag2)
                addToBackStack("second_fragment")
                setReorderingAllowed(true)
                commit()

            }
        }
    }
    }
