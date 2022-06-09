package com.alkemy.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.alkemy.movieapp.R
import com.alkemy.movieapp.models.Movie

class MovieAdapter(private val movies: List<Movie>, private val onClickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    //Funcion que le entrega al ViewHolder el layout que podra modificar con cada item de la lista de peliculas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    // Funcion que pasa por cada uno de los items que el MovieViewHolder ha creado y le entregara los datos de la funcion render
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.render(item, onClickListener)
    }

    //Funcion que devuelve el tamaño del listado que le hemos pasado
    override fun getItemCount(): Int = movies.size

}






