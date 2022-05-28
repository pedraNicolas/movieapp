package com.alkemy.movieapp.services

import com.alkemy.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/movie/popular?api_key=bb259118f01c72338a5e8a5dc72d7701")
    fun getMovieList(): Call<MovieResponse>

}