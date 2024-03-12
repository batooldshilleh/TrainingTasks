package com.example.fragmentapptask

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        btnNextA.setOnClickListener {
            val animal = etAnimal.text.toString()
            val fragmentB = FragmentB()
            val bundle = Bundle().apply {
                putString("animal", animal)
            }
            fragmentB.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_in_left
                )
                replace(R.id.fcvFragmints, fragmentB)
                addToBackStack("fragment_a")
                setReorderingAllowed(true)
                commit()
            }
        }

        btnBackA.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showName() {
        val name = arguments?.getString("name")
        val message = getString(R.string.fragment_name)
        val question = getString(R.string.frament_qustion)
        tvName.text = "$message $name $question"
    }
}
