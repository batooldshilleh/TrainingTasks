package com.example.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


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
        btnBackC.setSafeOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showColor() {
        tvInfo.showArgument(arguments, "color")
    }
}