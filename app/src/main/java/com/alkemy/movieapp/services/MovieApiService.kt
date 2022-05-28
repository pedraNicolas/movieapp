package com.alkemy.movieapp.services

import retrofit2.Retrofit

class MovieApiService {

    companion object{
        private const val BASE_URL="https://api.themoviedb.org/3"
        private var retrofit: Retrofit? =null
    }
}