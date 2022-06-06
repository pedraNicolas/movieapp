package com.alkemy.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.models.MovieResponse
import com.alkemy.movieapp.services.*
import com.alkemy.movieapp.views.SelectedMovie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)
        val cachedMovies = Cache.moviesMap["popular"]
        if (cachedMovies != null) {
            clickListener(cachedMovies)
            return
        }
        getMovieData("popular") { movies: List<Movie> ->
            clickListener(movies)
            Cache.moviesMap["popular"] = movies
        }
        /*Botones*/
        //Popular
        popularBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["popular"]
            if (cachedMovies != null) {
                clickListener(cachedMovies)
            } else {
                getMovieData("popular") { movies: List<Movie> ->
                    clickListener(movies)
                    Cache.moviesMap["popular"] = movies
                }
            }
        }
        //Latest NO FUNCIONA
        //Now Playing
        topRatedBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["top_rated"]
            if (cachedMovies != null) {
                clickListener(cachedMovies)
            } else {
                getMovieData("top_rated") { movies: List<Movie> ->
                    clickListener(movies)
                    Cache.moviesMap["top_rated"] = movies
                }
            }
        }
        //Now Playing
        nowPlayingBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["now_playing"]
            if (cachedMovies != null) {
                clickListener(cachedMovies)
            } else {
                getMovieData("now_playing") { movies: List<Movie> ->
                    clickListener(movies)
                    Cache.moviesMap["now_playing"] = movies
                }
            }
        }
        //Upcoming
        upcomingBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["upcoming"]
            if (cachedMovies != null) {
                clickListener(cachedMovies)
            } else {
                getMovieData("upcoming") { movies: List<Movie> ->
                    clickListener(movies)
                    Cache.moviesMap["upcoming"] = movies
                }
            }
        }
    }


    private fun getMovieData(id: String, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MoviePopularInterface::class.java)
        apiService.getMovieList(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

    private fun clickListener(movies: List<Movie>) {
        var adapter = MovieAdapter(movies)
        rvMovieList.adapter = adapter
        adapter.setOnItemClickListener(object : onItemClickListener {
            override fun onItemClick(position: Int) {

                intent = Intent(this@MainActivity, SelectedMovie::class.java).apply {
                    putExtra("movie", movies[position])
                }
                startActivity(intent)
            }
        })
    }
}


