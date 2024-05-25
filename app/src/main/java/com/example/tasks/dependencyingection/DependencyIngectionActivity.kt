package com.example.tasks.dependencyingection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.databinding.ActivityDependencyIngectionBinding
import javax.inject.Inject

class DependencyIngectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDependencyIngectionBinding


    @Inject
    lateinit var welcomeMessage: WelcomeMessage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDependencyIngectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        DaggerWelcomeComponent.create().inject(this)

        binding.btnShowMessage.setOnClickListener {
            val input = binding.etNameEnter.text.toString()
            val message = welcomeMessage.getWelcomeMessage(input)
            binding.tvMessage.text = message
        }
    }
}