package com.example.movieapi.movieList

import com.example.movieapi.data.model.PopularMovies

import com.example.movieapi.data.network.movieSearchApiFactory
import com.example.movieapi.data.network.popularMoviesApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private val view: View) {

    private val moviePopularMovieAPI = popularMoviesApiFactory.get()
    private val movieSearchAPI = movieSearchApiFactory.get()

    fun init() {

        CoroutineScope(Dispatchers.Main).launch {
            val movieResponse = withContext(Dispatchers.IO) {
                //moviePopularMovieAPI.getPopularMovies()
                movieSearchAPI.getMovieByName()
            }
            view.showWeatherList(movieResponse.moviesList)
        }
    }

    interface View {
        fun showWeatherList(weatherList: List<PopularMovies>)
    }


}