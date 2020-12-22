package com.example.movieapi.data.model

import com.google.gson.annotations.SerializedName

data class MovieCredit(
    @SerializedName("name")
    val director: String,
    @SerializedName("character")
    val cast: String
)