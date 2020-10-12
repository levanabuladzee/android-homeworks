package abuladze.levan.homework2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_details.*

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val bundle = intent.extras

        val title = bundle?.get("title")
        var description = bundle?.get("description")
        val isCompleted = bundle?.get("isCompleted")

        if (description == null) {
            description = "No Description"
        }

        tvDetailsTitle.text = title.toString()
        tvDetailsDescription.text = description.toString()
        tvDetailsCompleted.text = if (isCompleted as Boolean) "COMPLETED" else "IN PROGRESS"

        if (isCompleted) {
            tvDetailsCompleted.setTextColor(Color.parseColor("#1abc9c"))
        } else {
            tvDetailsCompleted.setTextColor(Color.parseColor("#f1c40f"))
        }
    }
}