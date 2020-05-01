package com.example.movieapi.movieList

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapi.R
import com.example.movieapi.data.model.PopularMovies
import kotlinx.android.synthetic.main.activity_movie_list.*


class MovieListActivity : AppCompatActivity(), MovieListPresenter.View {

    val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val presenter = MovieListPresenter(this)



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
        return true
        //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "seach selected", Toast.LENGTH_SHORT).show()

            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun showWeatherList(movieList: List<PopularMovies>) {
        adapter.addItems(movieList)
    }


}
