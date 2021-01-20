package abuladze.levan.afinal.network

import abuladze.levan.afinal.model.Coin
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinGeckoController {

    private val service: CoinGeckoService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(CoinGeckoService::class.java)
    }

    fun coins(targetCurrency: String, callback : Callback<List<Coin>>) {
        val call = service.coins(targetCurrency)
        call.enqueue(callback)
    }

    companion object {
        const val BASE_URL = "https://api.coingecko.com/api/v3/"
    }

}