package abuladze.levan.afinal.ui

import abuladze.levan.afinal.*
import abuladze.levan.afinal.adapter.ViewPagerAdapter
import abuladze.levan.afinal.database.CoinsDatabase
import abuladze.levan.afinal.repository.CoinGecko
import abuladze.levan.afinal.ui.coins.CoinsFragment
import abuladze.levan.afinal.ui.favourites.FavouritesFragment
import abuladze.levan.afinal.ui.settings.SettingsFragment
import abuladze.levan.afinal.viewmodel.SharedViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var coinGeckoClient: CoinGecko
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pagerAdapter: ViewPagerAdapter
    lateinit var ctx: Context
    lateinit var model: SharedViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("COINS_PREFERENCES", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("DARK_MODE", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        bottomNavigationView = findViewById(R.id.homeBottomNavView)
        ctx = this

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_coins_list -> {
                    viewPager.currentItem = 0
                }
                R.id.navigation_favourites -> {
                    viewPager.currentItem = 1
                }
                R.id.navigation_settings -> {
                    viewPager.currentItem = 2
                }
            }
            true
        }

        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.addFragment(CoinsFragment())
        pagerAdapter.addFragment(FavouritesFragment())
        pagerAdapter.addFragment(SettingsFragment())

        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
                super.onPageSelected(position)
            }
        })

        model = ViewModelProvider(this).get(SharedViewModel::class.java)
        model.sendDataSynced(false)

        coinGeckoClient = CoinGecko(this, sharedPreferences, model, CoinsDatabase.getDatabase(this).coinsDao())
        coinGeckoClient.fetchCoinsList()
    }

}