package com.example.tasks.constractCalcculatorNav

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.tasks.FragmentA
import com.example.tasks.R

class FragmantApp : AppCompatActivity() {
    private lateinit var btnNextActivity: Button
    private lateinit var teName: EditText
    private lateinit var fcvFragments: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragmant_app)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnNextActivity = findViewById(R.id.btnFragmint1)
        teName = findViewById(R.id.etName)
        fcvFragments = findViewById(R.id.fcvFragmints)
    }

    private fun setupClickListeners() {
        btnNextActivity.setOnClickListener {
            val name = teName.text.toString()
            val bundle = Bundle().apply {
                putString("name", name)
            }
            val fragmentA = FragmentA().apply {
                arguments = bundle
            }
            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right
                )
                replace(R.id.fcvFragmints, fragmentA)
                addToBackStack("activity_main")
                setReorderingAllowed(true)
                commit()
            }
        }
    }

}