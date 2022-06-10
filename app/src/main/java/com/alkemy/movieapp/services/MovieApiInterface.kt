package com.alkemy.movieapp.services

import com.alkemy.movieapp.models.MoviesListProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {
//        @GET("/3/movie/{id}?api_key=bb259118f01c72338a5e8a5dc72d7701&page=3")
//        fun getMovieList(@Path("id") id:String): Call<MovieResponse>
        @GET("/3/movie/{id}?api_key=bb259118f01c72338a5e8a5dc72d7701")
        fun getMovieList(@Path("id") id:String, @Query("page") page: Int): Call<MoviesListProvider>

    }


