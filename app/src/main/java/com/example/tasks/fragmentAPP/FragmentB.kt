package com.example.tasks.fragmentAPP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tasks.R


class FragmentB : Fragment(R.layout.fragment_b) {
    private lateinit var btnNextB: Button
    private lateinit var btnBackB: Button
    private lateinit var etColor: EditText
    private lateinit var tvAnimal: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        showAnimal()
        setupClickListeners()
    }

    private fun initViews(view: View) {
        btnBackB = view.findViewById(R.id.btnBackFragmentB)
        btnNextB = view.findViewById(R.id.btnNextFragmentB)
        etColor = view.findViewById(R.id.etColor)
        tvAnimal = view.findViewById(R.id.tvAnimal)
    }

    private fun setupClickListeners() {
        btnNextB.setSafeOnClickListener {
            val color = etColor.text.toString()
            val fragmentC = FragmentC().apply {
                arguments = Bundle().apply {
                    putString("color", color)
                }
            }
            navigateToFragment(fragmentC, "fragment_b")
        }

        btnBackB.setSafeOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showAnimal() {
        tvAnimal.showArgument(arguments, "animal")
    }

}