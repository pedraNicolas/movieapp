package com.alkemy.movieapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkemy.movieapp.R
import com.alkemy.movieapp.adapter.MovieAdapter
import com.alkemy.movieapp.databinding.ActivityMainBinding
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.models.MoviesListProvider
import com.alkemy.movieapp.services.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    //Variables
    private lateinit var binding: ActivityMainBinding
    var page = 1
    var pageId = "popular"
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    // Funcion que obtiene el listado de peliculas desde The Movie Database
    private fun getMovieData(page: Int, id: String, callback: (List<Movie>) -> Unit) {
        //Funcion que crea la Lista de peliculas
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
    fun initRecyclerView(movieList: List<Movie>) {
        layoutManager = LinearLayoutManager(this)
        rvMovieList.layoutManager = layoutManager
        rvMovieList.adapter = MovieAdapter(movieList) { onItemSelected(it) }
    }


    //Envia un objeto movie al Siguiente Activity al hacer click sobre un item
    private fun onItemSelected(movie: Movie) {
        println(movie.title)
        intent = Intent(this@MainActivity, SelectedMovie::class.java).apply {
            putExtra("movie", movie)
        }
        startActivity(intent)
    }


/*
            ON RESUME FUNCTION
*/

    override fun onResume() {
        val categories = resources.getStringArray(R.array.categories)
        button(pageId, page)
        val categoriesAdapter = ArrayAdapter(this, R.layout.list_item, categories)
        with(binding.autoCompleteTextView) {
            setAdapter(categoriesAdapter)
            onItemClickListener = this@MainActivity
        }
        nextBtn.setOnClickListener {
            page++
            Cache.moviesMap.remove(pageId)
            button(pageId, page)
        }
        previousBtn.setOnClickListener {
            if (page > 1) {
                page--
            } else {
                page
            }
            Cache.moviesMap.remove(pageId)
            button(pageId, page)
        }
        aboutMeTextView.setOnClickListener(){
            intent = Intent (this@MainActivity,AboutMe::class.java ).apply { }
            startActivity(intent)
        }
        super.onResume()
    }


    /*
                    ON DESTROY FUNCTION
     */

    //Vaciado de cache al destruir la app
    override fun onDestroy() {
        Cache.moviesMap.remove("popular")
        Cache.moviesMap.remove("top_rated")
        Cache.moviesMap.remove("now_playing")
        Cache.moviesMap.remove("upcoming")
        super.onDestroy()
    }

    // Verifico si existe el listado en cache, si no, realizo la consulta a la API
    fun button(pageId: String, page: Int) {
        if (Cache.moviesMap[pageId] != null) {
            initRecyclerView(Cache.moviesMap[pageId]!!)
        } else {
            getMovieData(page, pageId) { movies: List<Movie> ->
                initRecyclerView(movies)
                Cache.moviesMap[pageId] = movies
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        if (item != pageId) {
            page = 1
            when (item) {
                "Popular" -> pageId = "popular"
                "Top Rated" -> pageId = "top_rated"
                "Now Playing" -> pageId = "now_playing"
                "Upcoming" -> pageId = "upcoming"
            }
            Cache.moviesMap.remove(pageId)
        }
        button(pageId, page)
        return
    }

}







