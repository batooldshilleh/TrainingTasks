package com.example.tasks.navcomponant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasks.R
import com.example.tasks.bottombar.bottomBar

class TwoFragment : Fragment() {

    private lateinit var tvTow: TextView
    private lateinit var btnNextActivity: Button
    val args: TwoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_two, container, false)

        val myNumber = args.number.toString()
        tvTow = view.findViewById(R.id.tvTwo)
        tvTow.setText(myNumber)

        tvTow.setOnClickListener{
            findNavController().navigate(R.id.navToOne)
        }

        btnNextActivity = view.findViewById(R.id.btnNextActivity)

        btnNextActivity.setOnClickListener {
            startActivity(Intent(requireContext(), bottomBar::class.java))
        }


        return  view
    }


}
