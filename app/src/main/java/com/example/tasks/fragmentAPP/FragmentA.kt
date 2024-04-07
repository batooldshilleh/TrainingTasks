package com.example.tasks.fragmentAPP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tasks.R


class FragmentA : Fragment(R.layout.fragment_a) {

    private lateinit var btnNextA: Button
    private lateinit var btnBackA: Button
    private lateinit var etAnimal: EditText
    private lateinit var tvName: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        showName()
        setupClickListeners()
    }

    private fun initViews(view: View) {
        btnNextA = view.findViewById(R.id.btnNextFragmentA)
        btnBackA = view.findViewById(R.id.btnBackFragmentA)
        etAnimal = view.findViewById(R.id.etAnimal)
        tvName = view.findViewById(R.id.tvActivity)
    }

    private fun setupClickListeners() {
        btnNextA.setSafeOnClickListener {
            val animal = etAnimal.text.toString()
            val fragmentB = FragmentB().apply {
                arguments = Bundle().apply {
                    putString("animal", animal)
                }
            }
            navigateToFragment(fragmentB, "fragment_a")
        }

        btnBackA.setSafeOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showName() {

        tvName.showArgument(arguments, "name")
    }
    }

