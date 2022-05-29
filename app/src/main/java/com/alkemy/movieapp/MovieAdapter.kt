package com.alkemy.movieapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.movieapp.models.Movie

class MovieAdapter (
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder (view: View): RecyclerView.ViewHolder(view)   {

    }
}