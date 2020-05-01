package com.example.movieapi.data.network

import com.example.movieapi.data.model.PopularMovies
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface movieSearchApi {
    @GET("movie?api_key=fc10596101cb4714add74dcb40292721&language=en-US&query=was&page=3&include_adult=false")
    suspend fun getMovieByName(): movieSearchResponse
}

data class movieSearchResponse (
    @SerializedName("results")
    val moviesList: List<PopularMovies>
)

object movieSearchApiFactory {
    fun get(): movieSearchApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(" https://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(movieSearchApi::class.java)
    }
}