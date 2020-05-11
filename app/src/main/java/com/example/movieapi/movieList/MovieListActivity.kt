package com.example.movieapi.movieList

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapi.R
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
        presenter.init()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        return true
        //super.onCreateOptionsMenu(menu)

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@MovieListActivity, "Looking for $query", Toast.LENGTH_SHORT)
                    .show()
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(
                    this@MovieListActivity,
                    "Lookieng for $newText",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return false
            }
        })
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


    }

}
