
package com.example.fragmentapptask

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentC : Fragment(R.layout.fragment_c) {

    private lateinit var btnBackC: Button
    private lateinit var tvInfo: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupClickListeners()
        showColor()
    }

    private fun initViews(view: View) {
        btnBackC = view.findViewById(R.id.btnBackFragmentC)
        tvInfo = view.findViewById(R.id.tvChoise)
    }

    private fun setupClickListeners() {
        btnBackC.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showColor() {
        val color = arguments?.getString("color")
        val message = getString(R.string.fragment_color)
        tvInfo.text = "$message $color"
    }
}
