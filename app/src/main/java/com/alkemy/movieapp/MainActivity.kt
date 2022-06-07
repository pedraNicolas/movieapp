package com.alkemy.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.movieapp.adapter.MovieAdapter
import com.alkemy.movieapp.databinding.ActivityMainBinding
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.models.MoviesListProvider
import com.alkemy.movieapp.services.*
import com.alkemy.movieapp.views.SelectedMovie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    //Variables
    private lateinit var binding: ActivityMainBinding
    var page = 1
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Verifico si existe el listado en cache, si no, realiza la consulta
        val cachedMovies = Cache.moviesMap["popular"]
        if (cachedMovies != null) {
            initRecyclerView(cachedMovies)
            return
        }
        getMovieData(page, "popular") { movies: List<Movie> ->
            initRecyclerView(movies)
            Cache.moviesMap["popular"] = movies
        }
    }

    // Funcion que obtiene el listado de peliculas desde The Movie Database
    private fun getMovieData(page: Int, id: String, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)

        apiService.getMovieList(id, page).enqueue(object : Callback<MoviesListProvider> {
            override fun onResponse(
                call: Call<MoviesListProvider>,
                response: Response<MoviesListProvider>
            ) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MoviesListProvider>, t: Throwable) {

            }

        })
    }

    //Inicializa un RecyclerView LINEAL
    private fun initRecyclerView(movieList: List<Movie>) {
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
        binding.rvMovieList.adapter = MovieAdapter(movieList) { onItemSelected(it) }
    }

    //Envia un objeto movie al Siguiente Activity al hacer click sobre un item
    private fun onItemSelected(movie: Movie) {
        println(movie.title)
        intent = Intent(this@MainActivity, SelectedMovie::class.java).apply {
            putExtra("movie", movie)
        }
        startActivity(intent)
    }

    override fun onResume() {

        /*Botones*/
        //Popular
        popularBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["popular"]
            if (cachedMovies != null) {
                initRecyclerView(cachedMovies)
            } else {
                getMovieData(page, "popular") { movies: List<Movie> ->
                    initRecyclerView(movies)
                    Cache.moviesMap["popular"] = movies
                }
            }
        }
        //Latest NO FUNCIONA
        //Now Playing
        topRatedBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["top_rated"]
            if (cachedMovies != null) {
                initRecyclerView(cachedMovies)
            } else {
                getMovieData(page, "top_rated") { movies: List<Movie> ->
                    initRecyclerView(movies)
                    Cache.moviesMap["top_rated"] = movies
                }
            }
        }
        //Now Playing
        nowPlayingBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["now_playing"]
            if (cachedMovies != null) {
                initRecyclerView(cachedMovies)
            } else {
                getMovieData(page, "now_playing") { movies: List<Movie> ->
                    initRecyclerView(movies)
                    Cache.moviesMap["now_playing"] = movies
                }
            }
        }
        //Upcoming
        upcomingBtn.setOnClickListener {
            val cachedMovies = Cache.moviesMap["upcoming"]
            if (cachedMovies != null) {
                initRecyclerView(cachedMovies)
            } else {
                getMovieData(page, "upcoming") { movies: List<Movie> ->
                    initRecyclerView(movies)
                    Cache.moviesMap["upcoming"] = movies
                }
            }
        }
        super.onResume()
    }

//Vaciado de cache al destruir la app
    override fun onDestroy() {
        Cache.moviesMap.remove("popular")
        Cache.moviesMap.remove("top_rated")
        Cache.moviesMap.remove("now_playing")
        Cache.moviesMap.remove("upcoming")
        super.onDestroy()
    }
}





