package com.alkemy.movieapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.PointerIconCompat.load
import com.alkemy.movieapp.R
import com.alkemy.movieapp.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import kotlinx.android.synthetic.main.activity_selected_movie.*
import kotlinx.android.synthetic.main.movie_item.view.*

class SelectedMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_movie)
        val bundle =intent.extras
        val movie = bundle?.getParcelable<Movie>("movie")
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        titleId.text = movie?.title
        releaseDateId.text ="(${movie?.release_date})"
        Glide.with(this).load(IMAGE_BASE + movie?.poster_path).into(posterPathId)
        overviewTextView.text=movie?.overview

    }
}
