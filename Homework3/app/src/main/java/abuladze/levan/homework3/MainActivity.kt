package abuladze.levan.homework3

import abuladze.levan.homework3.api.MovieController
import abuladze.levan.homework3.model.ItemMovie
import abuladze.levan.homework3.model.Movie
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val movieController = MovieController()
    private val movies = arrayListOf<ItemMovie>()
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        val adapter = MovieAdapter(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(item: ItemMovie) {
                if (item.officialSite != "") {
                    startActivity(Intent(ACTION_VIEW, Uri.parse(item.officialSite)))
                }
            }
        })

        val dividerDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = adapter
        rvMovies.addItemDecoration(dividerDecoration)

        for (movieName in MovieNamesData.movieNames) {
            movieController.searchMovie(movieName, object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    movies.add(ItemMovie.transform(response.body()))
                    adapter.updateData(movies)
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("MainActivity", t.message.toString())
                }
            })
        }
    }
}