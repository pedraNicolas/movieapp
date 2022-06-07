package com.alkemy.movieapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.movieapp.databinding.MovieItemBinding
import com.alkemy.movieapp.models.Movie
import com.bumptech.glide.Glide

//Este objeto es llamado con cada uno de los objetos presente en el listado que le hemos pasado, en este caso
//con cada pelicula del listado de peliculas y "pintarlos" en el item creado (movie_item)

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = MovieItemBinding.bind(view)
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    fun render(movieModel: Movie, onClickListener: (Movie) -> Unit) {
        binding.movieTitle.text = movieModel.title
        binding.movieReleaseDate.text = movieModel.release_date
        Glide.with(binding.moviePoster.context).load(IMAGE_BASE + movieModel.poster_path)
            .into(binding.moviePoster)
        itemView.setOnClickListener { onClickListener(movieModel) }
    }
}