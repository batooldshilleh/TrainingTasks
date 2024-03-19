package com.example.tasks.navcomponant

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tasks.R
import com.google.android.material.navigation.NavigationView

class NavigationComponantApp : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var listener: NavController.OnDestinationChangedListener




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_componant_app)
        navigationView = findViewById(R.id.navigationView)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


    }

    override fun onStart() {
        super.onStart()

        initViews()
        navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
        changeBackground()
    }

    fun initViews(){
        navController = findNavController(R.id.fragmentContainerView)
        drawerLayout = findViewById(R.id.nave_drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }

    fun changeBackground(){
        listener = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            if(destination.id == R.id.oneFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.parpel)))

            }else if(destination.id == R.id.twoFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.magenta)))

            }
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }


}
