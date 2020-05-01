package com.example.movieapi.data.network

import com.example.movieapi.data.model.PopularMovies
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface popularMoviesApi {
    @GET("popular?api_key=fc10596101cb4714add74dcb40292721&language=en-Us&page=3")
    suspend fun getPopularMovies(): popularMoviesResponse
}

data class popularMoviesResponse(
    @SerializedName("results")
    val moviesList: List<PopularMovies>
)

object popularMoviesApiFactory {
    fun get(): popularMoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(popularMoviesApi::class.java)
    }
}