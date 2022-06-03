package com.alkemy.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)

        getMovieData("popular"){ movies: List<Movie> ->
            clickListener(movies)
        }
        /*Botones*/
        //Popular
        popularBtn.setOnClickListener{
            getMovieData("popular"){ movies: List<Movie> ->
                clickListener(movies)
            }
        }
        //Latest NO FUNCIONA
        //Now Playing
        topRatedBtn.setOnClickListener{
            getMovieData("top_rated"){ movies: List<Movie> ->
                clickListener(movies)
            }
        }
        //Now Playing
        nowPlayingBtn.setOnClickListener{
            getMovieData("now_playing"){ movies: List<Movie> ->
                clickListener(movies)
            }
        }
        //Upcoming
        upcomingBtn.setOnClickListener{
            getMovieData("upcoming"){ movies: List<Movie> ->
                clickListener(movies)
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
        private fun clickListener(movies: List<Movie>){
            var adapter = MovieAdapter(movies)
            rvMovieList.adapter = adapter
            adapter.setOnItemClickListener(object : onItemClickListener {
                override fun onItemClick(position: Int) {

                    intent = Intent(this@MainActivity,SelectedMovie::class.java).apply {
                        putExtra("movie", movies[position])
                    }
                    startActivity(intent)
                }
            })
        }
}


