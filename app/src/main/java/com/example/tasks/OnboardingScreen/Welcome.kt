package com.example.tasks.OnboardingScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tasks.R
import com.example.tasks.model.User

class Welcome : Fragment() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        firstName = view.findViewById(R.id.tvFirstName)
        lastName = view.findViewById(R.id.tvLastName)
        btnNext = view.findViewById(R.id.btnStartCustomObj)

        btnNext.setOnClickListener {
            val firstNameText = firstName.text.toString()
            val lastNameText = lastName.text.toString()

            val user = User(firstNameText,lastNameText)

            val action = WelcomeDirections.actionWelcomeToHomeOnboarding(user)
            findNavController().navigate(action)

        }

        return view
    }
}
