package com.example.tasks.fragmentapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.tasks.R

class FragmentSimpleTask : AppCompatActivity() {
    private lateinit var btnFirstFragment: Button
    private lateinit var btnSecondFragment: Button
    private lateinit var fragmentContener: FragmentContainerView

    val frag1 = FerstFragment()
    val frag2 = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment_simple_task)

        initViewss()
        defaultFragment()
        setupClickListeners()

    }

    fun initViewss() {
        btnFirstFragment = findViewById(R.id.btnFragment1)
        btnSecondFragment = findViewById(R.id.btn2)
        fragmentContener = findViewById(R.id.fcvFragment1)
    }

    fun defaultFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(fragmentContener.id, frag1)
            commit()
        }
    }

    fun setupClickListeners() {
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