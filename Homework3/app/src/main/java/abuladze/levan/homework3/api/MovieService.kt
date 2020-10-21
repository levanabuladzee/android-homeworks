package abuladze.levan.homework3.api

import abuladze.levan.homework3.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/singlesearch/shows")
    fun getMovie(@Query("q") movieName : String): Call<Movie>
}