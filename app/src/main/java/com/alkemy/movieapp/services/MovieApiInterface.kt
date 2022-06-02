package com.alkemy.movieapp.services

import com.alkemy.movieapp.MainActivity
import com.alkemy.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviePopularInterface {
        @GET("/3/movie/{id}?api_key=bb259118f01c72338a5e8a5dc72d7701")
        fun getMovieList(@Path("id") id:String): Call<MovieResponse>
    }


