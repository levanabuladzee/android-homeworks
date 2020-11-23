package abuladze.levan.homework6

import abuladze.levan.homework6.database.AppDatabase
import abuladze.levan.homework6.database.Car
import abuladze.levan.homework6.database.User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var btnPopulate : Button
    private lateinit var btnUserWithCars : Button
    private lateinit var btnDeleteAll : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPopulate = findViewById(R.id.btnPopulate)
        btnUserWithCars = findViewById(R.id.btnUserWithCars)
        btnDeleteAll = findViewById(R.id.btnDeleteAll)

        btnPopulate.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                AppDatabase.getDatabase(this@MainActivity).userDao().insertAll(
                    User(1, "John", "Doe"),
                    User(2, "Jane", "Doe")
                )
            }

            GlobalScope.launch(Dispatchers.IO) {
                // it can be auto incremented uid = 0
                AppDatabase.getDatabase(this@MainActivity).carDao().insertAll(
                    Car(1, 1,"Tesla", "Model Y"),
                    Car(2, 1,"Tesla", "Cybertruck"),
                    Car(3, 2,"Tesla", "Model Y"),
                    Car(4, 2,"Tesla", "Cybertruck"),
                    Car(5, 2, "Tesla", "Model 3")
                )
            }
        }

        btnUserWithCars.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                println(AppDatabase.getDatabase(this@MainActivity).userDao().getCarsAndUser())
            }
        }

        btnDeleteAll.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                AppDatabase.getDatabase(this@MainActivity).carDao().deleteAll()
                AppDatabase.getDatabase(this@MainActivity).userDao().deleteAll()
            }
        }
    }
}