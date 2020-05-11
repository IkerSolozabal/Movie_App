package com.example.movieapi.data.Repository

import com.example.movieapi.data.model.MovieCredit
import com.example.movieapi.data.model.MovieDetail
import com.example.movieapi.data.model.Movies
import retrofit2.http.Path

interface RemoteRepository {

    suspend fun getPopularMovies(): List<Movies>
    suspend fun getMovieByName(name: String): List<Movies>
    suspend fun getMovieById(id: String): MovieDetail
    suspend fun getCastById(id: String): MovieCredit
}