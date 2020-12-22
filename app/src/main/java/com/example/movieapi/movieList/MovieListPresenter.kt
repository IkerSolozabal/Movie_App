package com.example.movieapi.movieList

import android.accounts.NetworkErrorException
import com.example.movieapi.data.Repository.Remote.RetrofitRemoteRepository
import com.example.movieapi.data.Repository.RemoteRepository
import com.example.movieapi.data.model.MovieCredit
import com.example.movieapi.data.model.MovieDetail
import com.example.movieapi.data.model.Movies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private val view: View) {

    private val remoteRepository: RemoteRepository = RetrofitRemoteRepository()

    fun getPopularMovies() {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieList = withContext(Dispatchers.IO) {

                    remoteRepository.getPopularMovies()
                }
                view.showMovieList(movieList)
            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }
        }
    }

    fun getMoviesByName(movieName: String) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieList = withContext(Dispatchers.IO) {


                    remoteRepository.getMovieByName(movieName)
                }
                view.showMovieList(movieList)
            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }
        }
    }

    fun getMovieById(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val MovieDetail = withContext(Dispatchers.IO) {

                    remoteRepository.getMovieById(id)
                }
                view.showMovie(MovieDetail)

            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }
        }
    }

    fun getCastById(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val MovieCredit = withContext(Dispatchers.IO) {

                    remoteRepository.getCastById(id)
                }
                view.passMovieCredit(MovieCredit)

            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }
        }
    }

    interface View {
        fun showMovieList(weatherList: List<Movies>)
        fun showError(message: String)
        fun showMovie(movieDetail: MovieDetail)
        fun passMovieCredit(movieCredit: MovieCredit)
    }


}