package com.alkemy.movieapp.services

import com.alkemy.movieapp.models.Movie

object Cache {
     val moviesMap = mutableMapOf<String,List<Movie>>()
}