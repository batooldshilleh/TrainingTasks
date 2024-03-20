package com.example.tasks.onboard_screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tasks.R


class TheardScreen : Fragment() {
    private lateinit var tvEnd: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_theard_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        tvEnd=  view.findViewById(R.id.tvNextSplashThree)

        tvEnd.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_welcome)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }

    }