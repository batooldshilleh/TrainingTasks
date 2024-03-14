package com.example.tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton

class Login : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var forgetButton: Button
    private lateinit var adminButton: MaterialButton
    private lateinit var companyButton: MaterialButton
    private lateinit var clientButton: MaterialButton

    private var userType: UserType = UserType.COMPANY

    enum class UserType {
        ADMIN, COMPANY, CLIENT
    }

    private val userInfo = mapOf(
        "batool.shilleh@gmail.com" to Pair("Batool@8", UserType.ADMIN),
        "some@gmail.com" to Pair("Pass#45", UserType.COMPANY)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViewss()
        setupClickListeners()
        if (isLoggedIn()) {
            navigateToHome()
        }
    }

    private fun initViewss() {
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        forgetButton = findViewById(R.id.btnForgetPassword)
        adminButton = findViewById(R.id.dtnAdmin)
        companyButton = findViewById(R.id.dtnCompany)
        clientButton = findViewById(R.id.dtnClient)
    }

    private fun setupClickListeners() {
        adminButton.setOnClickListener { handleButtonClick(UserType.ADMIN) }
        companyButton.setOnClickListener { handleButtonClick(UserType.COMPANY) }
        clientButton.setOnClickListener { handleButtonClick(UserType.CLIENT) }

        loginButton.isEnabled = false

        email.addTextChangedListener {
            loginButton.isEnabled = it?.isNotBlank() == true && password.text.isNotBlank()
        }

        password.addTextChangedListener {
            loginButton.isEnabled = it?.isNotBlank() == true && email.text.isNotBlank()
        }

        loginButton.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            checkUserInfo(userEmail, userPassword)
        }

        forgetButton.setOnClickListener{
            navigateToForgetPassword()
        }
    }

    private fun handleButtonClick(type: UserType) {
        val buttonToSelect = when (type) {
            UserType.ADMIN -> adminButton
            UserType.COMPANY -> companyButton
            UserType.CLIENT -> clientButton
        }
        handleSingleSelection(buttonToSelect, type)
    }

    private fun handleSingleSelection(clickedButton: MaterialButton, type: UserType) {
        listOf(adminButton, companyButton, clientButton)
            .forEach { it.isChecked = false }

        clickedButton.isChecked = true
        userType = type
    }

    private fun checkUserInfo(userEmail: String, userPassword: String) {
        if (isValidEmail(userEmail)) {
            if (validateLogin(userEmail, userPassword)) {
                saveCredentials(userEmail, userPassword)
                navigateToHome()
            } else {
                toastMessage(getString(R.string.emailPasswordError))
            }
        } else {
            toastMessage(getString(R.string.invalidEmailFormat))
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateLogin(email: String, password: String): Boolean {
        return if (userInfo.containsKey(email)) {
            val (storedPassword, storedUserType) = userInfo[email]!!
            storedPassword == password && storedUserType == userType
        } else {
            toastMessage(getString(R.string.userTypeError))
            false
        }
    }

    private fun saveCredentials(email: String, password: String) {
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    private fun isLoggedIn(): Boolean {
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPrefs.getString("email", null)
        val userPassword = sharedPrefs.getString("password", null)
        return !userEmail.isNullOrEmpty() && !userPassword.isNullOrEmpty()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, Home::class.java).apply {
            putExtra("userType", userType.name)
        })
        finish()
    }

    private fun navigateToForgetPassword() {
        startActivity(Intent(this, ForgeatPassword::class.java))
        finish()
    }


    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}