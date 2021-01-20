package abuladze.levan.afinal.network

import abuladze.levan.afinal.model.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("coins/markets")
    fun coins(@Query("vs_currency") targetCurrency: String): Call<List<Coin>>
}