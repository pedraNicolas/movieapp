package com.alkemy.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.models.MovieResponse
import com.alkemy.movieapp.services.MovieApiInterface
import com.alkemy.movieapp.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface:: class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}