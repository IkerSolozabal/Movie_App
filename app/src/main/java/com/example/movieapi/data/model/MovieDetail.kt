package com.example.movieapi.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(

    @SerializedName("original_title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("name")
    val genre: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val image: String
)
