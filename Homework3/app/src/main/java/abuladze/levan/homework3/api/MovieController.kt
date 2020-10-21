package abuladze.levan.homework3.api

import abuladze.levan.homework3.model.Movie
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieController {
    private val service : MovieService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(MovieService::class.java)
    }

    fun searchMovie(movieName : String, callback: Callback<Movie>) {
        val call = service.getMovie(movieName)
        call.enqueue(callback)
    }

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}