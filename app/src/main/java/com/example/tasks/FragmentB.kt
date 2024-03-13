package com.example.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


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
        btnNextB.setOnClickListener {
            val color = etColor.text.toString()
            val fragmentC = FragmentC()
            val bundle = Bundle().apply {
                putString("color", color)
            }
            fragmentC.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_in_left
                )
                replace(R.id.fcvFragmints, fragmentC)
                addToBackStack("fragment_b")
                setReorderingAllowed(true)
                commit()
            }
        }

        btnBackB.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showAnimal() {
        val animal = arguments?.getString("animal")
        val message = getString(R.string.fragment_animal)
        tvAnimal.text = "$message $animal"
    }

}