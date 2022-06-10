package com.alkemy.movieapp.views

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.core.view.PointerIconCompat.load
import com.alkemy.movieapp.R
import com.alkemy.movieapp.databinding.ActivityMainBinding
import com.alkemy.movieapp.databinding.ActivitySelectedMovieBinding
import com.alkemy.movieapp.models.Movie
import com.alkemy.movieapp.services.MovieApiInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import kotlinx.android.synthetic.main.activity_selected_movie.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlin.math.ceil
import kotlin.math.floor

class SelectedMovie : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val movie = bundle?.getParcelable<Movie>("movie")
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        binding.titleId.text = movie?.title
        binding.releaseDateId.text = "(${movie?.release_date})"
        with(binding.posterPathId.context).load(IMAGE_BASE + movie?.poster_path)
            .into(binding.posterPathId)
        binding.overviewTextView.text = movie?.overview
        val voteAverage = movie?.vote_average
        when (voteAverage!!) {
            in 0.5..2.5 -> {
                oneStars.visibility = View.VISIBLE
            }
            in 2.5..4.5 -> {
                oneStars.visibility = View.VISIBLE
                twoStars.visibility = View.VISIBLE
            }
            in 4.5..6.5 -> {
                oneStars.visibility = View.VISIBLE
                twoStars.visibility = View.VISIBLE
                threeStars.visibility = View.VISIBLE
            }
            in 6.5..8.5 -> {
                oneStars.visibility = View.VISIBLE
                twoStars.visibility = View.VISIBLE
                threeStars.visibility = View.VISIBLE
                fourStars.visibility = View.VISIBLE
            }
            else->{
                oneStars.visibility = View.VISIBLE
                twoStars.visibility = View.VISIBLE
                threeStars.visibility = View.VISIBLE
                fourStars.visibility = View.VISIBLE
                fiveStars.visibility = View.VISIBLE
            }
        }

    }
}
