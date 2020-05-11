package com.example.movieapi.movieList

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapi.MovieDetail.MovieDetailActivity
import com.example.movieapi.R
import com.example.movieapi.data.model.MovieCredit
import com.example.movieapi.data.model.MovieDetail
import com.example.movieapi.data.model.Movies
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieListPresenter.View {

    val presenter = MovieListPresenter(this)

    val onMovieClickListener = object : MovieListAdapter.OnMovieClickListener {

        override fun onMovieClick(movies: Movies) {

            Toast.makeText(
                this@MovieListActivity, "${movies.title}",
                Toast.LENGTH_SHORT
            ).show()

            presenter.getMovieById("${movies.id}")
            presenter.getCastById("${movies.id}")

        }

    }

    val adapter = MovieListAdapter(onMovieClickListener)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        movieList.layoutManager = LinearLayoutManager(this)
        movieList.setHasFixedSize(true)
        movieList.adapter = adapter
        movieList.addItemDecoration(
            DividerItemDecoration(
                this,
                (movieList.layoutManager as LinearLayoutManager).orientation
            )
        )
        presenter.getPopularMovies()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
        //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieList(movieList: List<Movies>) {
        adapter.addItems(movieList)
    }

    override fun showError(message: String) {

        Toast.makeText(
            this@MovieListActivity, message,
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun showMovie(movieDetail: MovieDetail) {
        val intent =
            Intent(this@MovieListActivity, MovieDetailActivity::class.java)

        intent.putExtra("title", "${movieDetail.title}")
        intent.putExtra("voteAverage", "${movieDetail.voteAverage}")
        intent.putExtra("releaseDate", "${movieDetail.releaseDate}")
        intent.putExtra("image", "${movieDetail.image}")
        intent.putExtra("overview", "${movieDetail.overview}")

        startActivity(intent)
    }

    override fun passMovieCredit(movieCredit: MovieCredit) {

    }

    //override fun passMovieCredit(movieCredit: MovieCredit) {
    //intent.putExtra("cast", "${movieCredit.cast}")
    //intent.putExtra("voteAverage", "${movieCredit.director}")
    //  startActivity(intent)
    //}

}
