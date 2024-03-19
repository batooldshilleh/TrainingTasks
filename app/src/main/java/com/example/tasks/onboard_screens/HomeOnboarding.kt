package com.example.tasks.onboard_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.tasks.R


class HomeOnboarding : Fragment() {

    private val args by navArgs<HomeOnboardingArgs>()
    private lateinit var tvUser: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_onboarding, container, false)

        tvUser = view.findViewById(R.id.tvCreateAccount)

        val welcomeMessage = getString(R.string.welcom) + " ${args.curantUser.firstName} ${args.curantUser.lastName}"
        tvUser.text = welcomeMessage
        return view
    }


}