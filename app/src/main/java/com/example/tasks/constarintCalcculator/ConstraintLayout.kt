package com.example.tasks.constarintCalcculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.constractCalcculatorNav.NavigationConstarint
import com.example.tasks.R

class ConstraintLayout : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivision: Button
    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var resultTv: TextView
    private lateinit var btnMove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)

        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSubtraction)
        btnMultiply = findViewById(R.id.btnMultiplication)
        btnDivision = findViewById(R.id.btnDivision)
        etA = findViewById(R.id.etA)
        etB = findViewById(R.id.etB)
        resultTv = findViewById(R.id.resultTv)
        btnMove = findViewById(R.id.nave)
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        val v: View.OnClickListener = object: View.OnClickListener {
            override fun onClick(p0: View?) {
                navigateToActivity2()
            }

        }
        btnMove.setOnClickListener(v)
        btnMove.setOnClickListener(null)
        btnMove.setOnClickListener { _ -> }

        btnMove.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                navigateToActivity2()
            }

        })

        btnMove.setOnClickListener(::testOnClick)
    }

    fun testOnClick(v : View?){

    }
    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        val firstNumberText = etA.text.toString()
        val firstNumber = if (firstNumberText.isNotEmpty()) firstNumberText.toDouble() else 0.0

        var secondNumberText = etB.text.toString()
        var secondNumber = if (secondNumberText.isNotEmpty()) secondNumberText.toDouble() else 0.0

        var result = 0.0

        if (v?.id == R.id.nave) {
            navigateToActivity2()
            return
        }

        v?.let {

            when (it.id) {
                R.id.btnAdd -> {
                    result = firstNumber + secondNumber
                }

                R.id.btnSubtraction -> {
                    result = firstNumber - secondNumber
                }

                R.id.btnMultiplication -> {
                    result = firstNumber * secondNumber
                }

                R.id.btnDivision -> {
                    result = firstNumber / secondNumber
                }


            }
            resultTv.text = "the result = $result"
        }

    }


    private inline fun navigateToActivity2() {
        val intent = Intent(this@ConstraintLayout, NavigationConstarint::class.java).apply {
            putExtra("name",10)
            putExtra("name",10)
        }
        intent.putExtra("string",15.2)
        startActivity(intent)
        return
    }

}