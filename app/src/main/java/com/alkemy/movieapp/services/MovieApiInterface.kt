package com.alkemy.movieapp.services

import com.alkemy.movieapp.MainActivity
import com.alkemy.movieapp.item2
import com.alkemy.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

val get_url:String ="/3/movie/$item2?api_key=bb259118f01c72338a5e8a5dc72d7701"
interface MovieApiInterface {
    @GET(get_url)
    fun getMovieList(): Call<MovieResponse>

}