package com.example.tasks.naveComponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tasks.R


class OneFragment : Fragment() {

    private lateinit var tvOne: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)

        tvOne = view.findViewById(R.id.tvOne)

        tvOne.setOnClickListener {
            val action = OneFragmentDirections.navToTwo(88)
            findNavController().navigate(action)


        }

        return view
    }


}