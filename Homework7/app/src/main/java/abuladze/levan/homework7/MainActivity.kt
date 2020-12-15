package abuladze.levan.homework7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var btnRun1: Button

    private lateinit var btnRun2: Button

    private lateinit var btnStop1: Button

    private lateinit var job1: Job

    private lateinit var job2: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRun1 = findViewById(R.id.btnRun1)
        btnRun2 = findViewById(R.id.btnRun2)
        btnStop1 = findViewById(R.id.btnStop1)

        btnRun1.setOnClickListener {
            job1 = coroutineScope.launch {
                btnRun1.isEnabled = false
                val itCount = execute1()
                Toast.makeText(this@MainActivity, "Job 1: Finished $itCount", Toast.LENGTH_SHORT).show()
                btnRun1.isEnabled = true
            }
        }

        btnRun2.setOnClickListener {
            job2 = coroutineScope.launch {
                btnRun2.isEnabled = false
                val itCount = execute2()
                Toast.makeText(this@MainActivity, "Job 2: Finished $itCount", Toast.LENGTH_SHORT).show()
                btnRun2.isEnabled = true
            }
        }

        btnStop1.setOnClickListener {
            if (this::job1.isInitialized) {
                job1.cancel()
            }
            if (this::job2.isInitialized) {
                job2.cancel()
            }
            btnRun1.isEnabled = true
            btnRun2.isEnabled = true
        }
    }

    private suspend fun execute1(): Long {
        return withContext(Dispatchers.Default) {
            Log.d(TAG, "started job 1")

            val max = 10000000L

            var iterationsCount: Long = 0
            for (i in 1..max) {
                if (!this.isActive) {
                    Log.d(TAG, "job 1 not active anymore")
                    break
                }
                iterationsCount++
            }

            Log.d(TAG, "job 1 completed with iteration $iterationsCount")

            iterationsCount
        }
    }

    private suspend fun execute2(): Long {
        return withContext(Dispatchers.Default) {
            Log.d(TAG, "started job 2")

            val max = 10000000L

            var iterationsCount: Long = 0
            for (i in 1..max) {
                if (!this.isActive) {
                    Log.d(TAG, "job 2 not active anymore")
                    break
                }
                iterationsCount++
            }

            Log.d(TAG, "job 2 completed with iteration $iterationsCount")

            iterationsCount
        }
    }

    override fun onStop() {
        super.onStop()
        if (this::job1.isInitialized) {
            job1.cancel()
        }
        if (this::job2.isInitialized) {
            job2.cancel()
        }
        btnRun1.isEnabled = true
        btnRun2.isEnabled = true
    }

    companion object {
        const val TAG = "MainActivity"
    }
}