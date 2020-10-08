package abuladze.levan.homework_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)
        val usernameEditText = findViewById<View>(R.id.username_editText) as EditText

        startButton.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)

            val username = usernameEditText.text.toString().trim()

            if (username != "") {
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }
    }
}