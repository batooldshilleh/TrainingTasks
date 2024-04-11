package com.example.tasks.fibCoroutins

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasks.R
import com.example.tasks.databinding.ActivityFibBinding
import com.example.tasks.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class FibActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFibBinding
    val TAG = "FibActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFibBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "starting calculation..")
            withTimeout(3000L) {
                for (i in 30..40) {
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }
            Log.d(TAG, "end calculation..")
        }

        /*  runBlocking {
            delay(3000L)
              job.cancel()
              Log.d(TAG,"canceld job")

          }*/

    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}