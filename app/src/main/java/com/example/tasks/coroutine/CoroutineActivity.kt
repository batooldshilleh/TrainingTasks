package com.example.tasks.coroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {

    val TAG = "CoroutineActivity"
    private lateinit var binding: ActivityCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)





        Log.d(TAG, "Before run blocking ${Thread.currentThread().name}")
        runBlocking {
            launch(Dispatchers.IO) {
                val answer = doNetworkCall()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "launch code  ${Thread.currentThread().name}")
                    binding.corText.text = answer
                }
            }
            launch(Dispatchers.IO) {
                val answer = doNetworkCall2()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "launch code  ${Thread.currentThread().name}")
                    binding.corText2.text = answer
                }
            }
            Log.d(TAG, "Start blocking ${Thread.currentThread().name}")
            delay(5000L)
            //Thread.sleep(5000L)
            Log.d(TAG, "End run blocking ${Thread.currentThread().name}")
        }
        Log.d(TAG, "After run blocking ${Thread.currentThread().name}")

    }

}


suspend fun doNetworkCall(): String {
    delay(3000L)
    return "This is the answer"
}

suspend fun doNetworkCall2(): String {
    delay(3000L)
    return "This is the answer2"
}

