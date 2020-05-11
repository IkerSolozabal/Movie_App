package com.example.movieapi.movieList

import android.accounts.NetworkErrorException
import com.example.movieapi.data.Repository.Remote.RetrofitRemoteRepository
import com.example.movieapi.data.Repository.RemoteRepository
import com.example.movieapi.data.model.Movies
import com.example.movieapi.data.network.movieApiFactory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private val view: View) {

    private val movieAPI = movieApiFactory.get()

    private val remoteRepository: RemoteRepository = RetrofitRemoteRepository()

    fun init() {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieList = withContext(Dispatchers.IO) {

                    remoteRepository.getPopularMovies()
                    //remoteRepository.getMovieByName("was")
                }
                view.showMovieList(movieList)
            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }
        }
    }

    interface View {
        fun showMovieList(weatherList: List<Movies>)
        fun showError(message: String)
    }


}