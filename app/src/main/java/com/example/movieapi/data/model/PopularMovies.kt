package com.example.movieapi.data.model

import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float
)