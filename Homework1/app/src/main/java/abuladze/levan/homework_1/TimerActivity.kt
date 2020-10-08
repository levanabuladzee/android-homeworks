package abuladze.levan.homework_1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class TimerActivity : AppCompatActivity() {
    private var seconds = 0
    private var running = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val usernameTextView = findViewById<TextView>(R.id.username_textView)
        val username = intent.getStringExtra("username").toString()

        usernameTextView.text = "Hello $username"

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    fun onClickStart(view: View?) {
        running = true
    }
    fun onClickPause(view: View?) {
        running = false
    }
    fun onClickReset(view: View?) {
        running = false
        seconds = 0
    }

    fun onClickShare(view: View?) {
        val intent = Intent(Intent.ACTION_SEND)
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        val secs = seconds % 60
        val time = String.format(
            Locale.getDefault(),
            "%d:%02d:%02d", hours, minutes, secs
        )
        val shareBody = "Check my current timestamp: $time"
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(intent, getString(R.string.share_using)));
    }
    
    private fun runTimer() {
        val timeView = findViewById<TextView>(R.id.time_view)
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, secs
                )
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}