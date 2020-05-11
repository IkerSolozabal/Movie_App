package com.example.movieapi.MovieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.movieapi.R
import com.example.movieapi.data.model.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        val movieImage = findViewById<ImageView>(R.id.movieImage)

        var urlPhoto = intent.getStringExtra("image")
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + urlPhoto).into(movieImage)


        var movieTitle = intent.getStringExtra("title")

        movieTitleTxt.text = movieTitle

        val voteAverage = intent.getStringExtra("voteAverage")

        movieVoteAverageTxt.text = voteAverage.toString()

        var movieReleaseDate = intent.getStringExtra("releaseDate")

        movieReleaseDateTxt.text = movieReleaseDate

        var overview = intent.getStringExtra("overview")

        movieOverviewTxt.text = overview

        var director = intent.getStringExtra("director")

        movieDirectorTxt.text = director

        var cast = intent.getStringExtra("cast")

        movieCastTxt.text = cast



        shareIcon.setOnClickListener {
            Toast.makeText(this, "Share Cliked", Toast.LENGTH_SHORT).show()
        }
        bookmarkIcon.setOnClickListener {
            Toast.makeText(this, "Stars Cliked", Toast.LENGTH_SHORT).show()
        }
        addIcon.setOnClickListener {
            Toast.makeText(this, "Add cliked", Toast.LENGTH_SHORT).show()
        }
    }
}
