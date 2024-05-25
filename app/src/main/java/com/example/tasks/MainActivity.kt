package com.example.tasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            navigate("com.example.tasks.fragmints.FragmantApp")
        }

        binding.btnTask6.setOnClickListener {
            navigate("com.example.tasks.recyclerViewHome.RecyclerViewListsActivity")
        }




    }


    fun navigate(className: String, requestCode: Int? = null) {

        val intent = Intent(this@MainActivity, Class.forName(className))

        startActivity(intent)


    }


}
