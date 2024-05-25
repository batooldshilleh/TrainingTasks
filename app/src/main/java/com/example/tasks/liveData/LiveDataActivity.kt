package com.example.tasks.liveData

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityDependencyIngectionBinding
import com.example.tasks.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: ActivityLiveDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

        binding.btnShowMessage.setOnClickListener {
            val name = binding.etNameEnter.text.toString()
            viewModel.generateWelcomeMessage(name)
        }

        viewModel.welcomeMessage.observe(this, Observer { message ->
            binding.tvMessage.text = message
        })
    }
}