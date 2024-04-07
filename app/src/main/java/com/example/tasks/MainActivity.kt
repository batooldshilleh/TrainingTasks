package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnLinearLayout: Button
    private lateinit var btnFragment: Button
    private lateinit var btnConstraintLayout: Button
    private lateinit var btnLogin: Button
    private lateinit var btnNavigationComponant: Button
    private lateinit var btnOnboarding: Button
    private lateinit var btnFragmentApp: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()
    }



    fun setupView() {

        btnLinearLayout = findViewById(R.id.btnTask1)
        btnFragment = findViewById(R.id.btnTask4)
        btnConstraintLayout = findViewById(R.id.btnTask2)
        btnLogin = findViewById(R.id.btnTask3)
        btnNavigationComponant = findViewById(R.id.btnTask6)
        btnOnboarding = findViewById(R.id.btnTask7)
        btnFragmentApp = findViewById(R.id.btnTask5)
    }


  fun  setupListener(){
        btnLinearLayout.setOnClickListener {
            navigate("com.example.tasks.linearCalcculater.LinearLayout")
        }

        btnFragment.setOnClickListener {
            navigate("com.example.tasks.fragmintSimple.FragmentSimpleTask")

        }

        btnLogin.setOnClickListener {
            navigate("com.example.tasks.login.Login")
        }


        btnConstraintLayout.setOnClickListener {
            navigate("com.example.tasks.constarintCalcculator.ConstraintLayout")

        }


        btnNavigationComponant.setOnClickListener {
            navigate("com.example.tasks.naveComponent.NavigationComponantApp")
        }

        btnOnboarding.setOnClickListener {
            navigate("com.example.tasks.splash.Onbording")
        }
    }

      btnFragmentApp.setOnClickListener {
          navigate("com.example.tasks.fragmentAPP.FragmantApp")
      }


  
}


    fun navigate(className: String) {

        val intent = Intent(this@MainActivity, Class.forName(className))
        startActivity(intent)

    }


}

