package com.example.tasks.linearlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.linearnav.Navigation
import com.example.tasks.R

class LinearLayout : AppCompatActivity(), View.OnClickListener {



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
            setContentView(R.layout.activity_linear_layout)

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
            btnMove.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            var firstNumberText = etA.text.toString()
            var firstNumber = if (firstNumberText.isNotEmpty()) firstNumberText.toDouble() else 0.0

            var secondNumberText = etB.text.toString()
            var secondNumber =
                if (secondNumberText.isNotEmpty()) secondNumberText.toDouble() else 0.0

            var result = 0.0

            if (v?.id == R.id.nave) {
                navigateToActivity2()
                return
            }

            when (v?.id) {
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


        private inline fun navigateToActivity2() {
            val intent = Intent(this@LinearLayout, Navigation::class.java)
            startActivity(intent)
            return
        }
    }
