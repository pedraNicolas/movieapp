package com.alkemy.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.models.MovieResponse
import com.alkemy.movieapp.services.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext.get

    class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)

        getMovieData("popular"){ movies: List<Movie> ->
            rvMovieList.adapter = MovieAdapter(movies)
        }
                    //Botones
        //Popular
        popularBtn.setOnClickListener{
            getMovieData("popular"){ movies: List<Movie> ->
                rvMovieList.adapter = MovieAdapter(movies)
            }
        }
        //Latest NO FUNCIONA
        //Now Playing
        topRatedBtn.setOnClickListener{
            getMovieData("top_rated"){ movies: List<Movie> ->
                rvMovieList.adapter = MovieAdapter(movies)
            }
        }
        //Now Playing
        nowPlayingBtn.setOnClickListener{
            getMovieData("now_playing"){ movies: List<Movie> ->
                rvMovieList.adapter = MovieAdapter(movies)
            }
        }
        //Upcoming
        upcomingBtn.setOnClickListener{
            getMovieData("upcoming"){ movies: List<Movie> ->
                rvMovieList.adapter = MovieAdapter(movies)
            }
        }
    }

    private fun getMovieData(id:String,callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MoviePopularInterface::class.java)
        apiService.getMovieList(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}


