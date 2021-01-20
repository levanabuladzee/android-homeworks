package abuladze.levan.afinal.repository

import abuladze.levan.afinal.database.Coins
import abuladze.levan.afinal.database.CoinsDao
import abuladze.levan.afinal.model.Coin
import abuladze.levan.afinal.network.CoinGeckoController
import abuladze.levan.afinal.viewmodel.SharedViewModel
import abuladze.levan.afinal.util.NetworkUtils
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CoinGecko(
    private val ctx: Context?,
    private val sharedPreferences: SharedPreferences?,
    private val model: SharedViewModel,
    private val dao: CoinsDao
    ) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val coinGeckoController = CoinGeckoController()
    private val editor = sharedPreferences?.edit()

    fun fetchCoinsList() {
        if (ctx != null) {
            fetchCoinsList(null, false)

            if (NetworkUtils.isConnected(ctx)) {
                coinGeckoController.coins("usd", object : Callback<List<Coin>> {
                    override fun onResponse(call: Call<List<Coin>>, response: Response<List<Coin>>) {
                        fetchCoinsList(response, true)
                    }

                    override fun onFailure(call: Call<List<Coin>>, t: Throwable) {
                        Log.e("MainActivity", t.message.toString())
                    }
                })
            }
        }
    }

    fun fetchCoinsList(response: Response<List<Coin>>?, online: Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            if (online) {
                val favouriteSymbols = dao.favouriteSymbols()
                val customCoins = toCoinsListEntity(response!!, favouriteSymbols)
                dao.deleteAll()
                dao.insertAll(customCoins)
                saveSyncTime()
            }

            val allCoinsList = dao.all()
            val favCoinsList = dao.favourites()

            coroutineScope.launch {
                sync(allCoinsList, favCoinsList)
                model.sendDataSynced(true)
            }
        }
    }

    private fun toCoinsListEntity(
            response: Response<List<Coin>>,
            favouriteSymbols: List<String>
    ): List<Coins> {
        return response.body().let {
            it!!.filter { item ->
                item.symbol.isNullOrEmpty().not()
            }.map { item ->
                Coins(
                    item.symbol ?: "",
                    item.id,
                    item.name,
                    item.price,
                    item.changePercent,
                    item.image,
                    favouriteSymbols.contains(item.symbol)
                )
            }
        }
    }

    private fun saveSyncTime() {
        if (editor != null) {
            val c = Calendar.getInstance()
            editor.putString("LAST_SYNC", "${convertTime(c.get(Calendar.HOUR_OF_DAY))}:${convertTime(c.get(Calendar.MINUTE))}:${convertTime(c.get(Calendar.SECOND))}")
            editor.apply()
        }
    }

    private fun sync(
        allCoinsList: List<Coins>,
        favCoinsList: List<Coins>
    ) {
        if (sharedPreferences != null) {
            model.sendSync("Last synced ${sharedPreferences.getString("LAST_SYNC", "?")}")
            model.sendAllCoinsList(allCoinsList)
            model.sendFavCoinsList(favCoinsList)
        }
    }

    private fun convertTime(input: Int): String {
        return if (input >= 10) {
            input.toString()
        } else {
            "0$input"
        }
    }
}