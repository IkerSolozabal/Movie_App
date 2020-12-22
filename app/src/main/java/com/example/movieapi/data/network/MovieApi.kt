package com.example.movieapi.data.network

import com.example.movieapi.data.model.MovieCredit
import com.example.movieapi.data.model.MovieDetail
import com.example.movieapi.data.model.Movies
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular?api_key=fc10596101cb4714add74dcb40292721&language=en-Us&page=3")
    suspend fun getPopularMovies(): popularMoviesResponse

    @GET("search/movie?api_key=fc10596101cb4714add74dcb40292721&language=en-US&query={name}&page=3&include_adult=false")
    suspend fun getMovieByName(@Path("name") name: String): movieByNameResponse

    @GET("movie/{id}?api_key=fc10596101cb4714add74dcb40292721&language=en-US")
    suspend fun getMovieById(@Path("id") id: String): MovieDetail

    @GET("movie/{id}/credits?api_key=fc10596101cb4714add74dcb40292721")
    suspend fun getCastById(@Path("id") id: String): MovieCredit
}


data class popularMoviesResponse(
    @SerializedName("results")
    val moviesList: List<Movies>
)

data class movieByNameResponse(
    @SerializedName("results")
    val moviesList: List<Movies>
)

object movieApiFactory {
    fun get(): MovieApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieApi::class.java)
    }
}