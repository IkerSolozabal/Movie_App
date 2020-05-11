package com.example.movieapi.data.Repository.Remote

import android.accounts.NetworkErrorException
import com.example.movieapi.data.Repository.RemoteRepository
import com.example.movieapi.data.model.MovieCredit
import com.example.movieapi.data.model.MovieDetail
import com.example.movieapi.data.model.Movies
import com.example.movieapi.data.network.*
import java.lang.Exception

class RetrofitRemoteRepository : RemoteRepository {

    private val movieApi = movieApiFactory.get()

    override suspend fun getPopularMovies(): List<Movies> {
        try {
            val response = movieApi.getPopularMovies()
            return response.moviesList

        } catch (e: Exception) {
            throw NetworkErrorException("Error fetching data")
            //return emptyList()
        }
    }

    override suspend fun getMovieByName(name: String): List<Movies> {
        try {
            val response = movieApi.getMovieByName(name)
            return response.moviesList

        } catch (e: Exception) {
            throw NetworkErrorException("Error fetching data")
            //return emptyList()
        }
    }

    override suspend fun getMovieById(id: String): MovieDetail {
        try {
            val response = movieApi.getMovieById(id)
            return response

        } catch (e: Exception) {
            throw NetworkErrorException("Error fetching data")
            //return emptyList()
        }
    }

    override suspend fun getCastById(id: String): MovieCredit {
        try {
            val response = movieApi.getCastById(id)
            return response

        } catch (e: Exception) {
            throw NetworkErrorException("Error fetchings data")
            //return emptyList()
        }
    }

}