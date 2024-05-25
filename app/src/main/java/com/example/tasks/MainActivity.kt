package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.tasks.databinding.ActivityLiveDataBinding
import com.example.tasks.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupListener()
    }


    fun setupListener() {
        binding.btnTask1.setOnClickListener {
            navigate("com.example.tasks.linearCalcculater.LinearLayout")
        }

        binding.btnTask2.setOnClickListener {
            navigate("com.example.tasks.constarintCalcculator.ConstraintLayout")

        }

        binding.btnTask3.setOnClickListener {
            navigate("com.example.tasks.login.Login")
        }

        binding.btnTask4.setOnClickListener {
            navigate("com.example.tasks.fragmintSimple.FragmentSimpleTask")

        }

        binding.btnTask5.setOnClickListener {
            navigate("com.example.tasks.fragmentAPP.FragmantApp")
        }

        binding.btnTask6.setOnClickListener {
            navigate("com.example.tasks.naveComponent.NavigationComponantApp")
        }


        binding.btnTask7.setOnClickListener {
            navigate("com.example.tasks.splash.Onbording")
        }


        binding.btnTask9.setOnClickListener {
            navigate("com.example.tasks.composLab.FirstComposeApp")
        }

        binding.btnTask10.setOnClickListener {
            navigate("com.example.tasks.dependencyingection.DependencyIngectionActivity")
        }

        binding.btnTask11.setOnClickListener {
            navigate("com.example.tasks.liveData.LiveDataActivity")
        }

        binding.btnTask12.setOnClickListener {
            navigate("com.example.tasks.composLab.RealWorldDesign")
        }

        binding.btnTask13.setOnClickListener {
            navigate("com.example.tasks.codLabState.ComposeStateActivity")
        }


        binding.btnTask14.setOnClickListener {
            navigate("com.example.tasks.coroutine.CoroutineActivity")
        }

    }


    fun navigate(className: String, requestCode: Int? = null) {

        val intent = Intent(this@MainActivity, Class.forName(className))

            startActivity(intent)


    }


}

