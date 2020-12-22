package com.example.movieapi.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.R
import com.example.movieapi.data.model.Movies
import com.squareup.picasso.Picasso

class MovieListAdapter(val onMovieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private var movieList: List<Movies> = emptyList()

    fun addItems(movieList: List<Movies>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie, onMovieClickListener)
    }

    class MovieViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        private val posterImg = view.findViewById<ImageView>(R.id.moviePoster)
        private var movieTitle = view.findViewById<TextView>(R.id.movieTitleTxt)
        private var movieReleaseDate = view.findViewById<TextView>(R.id.movieReleaseDateTxt)
        private var movieVoteAverage = view.findViewById<TextView>(R.id.movieVoteAverageTxt)
        private var movieOverview = view.findViewById<TextView>(R.id.movieOverviewTxt)

        fun bind(movies: Movies, onMovieClickListener: OnMovieClickListener) {
            with(movies) {
                val imageUrl: String

                if (posterPath == null) {
                    imageUrl =
                        "https://siempreenmedio.files.wordpress.com/2014/04/no_disponible.jpg?w=400&h=400&crop=1&zoom=2"
                    movieOverview.maxLines = 3
                } else {
                    imageUrl = "https://image.tmdb.org/t/p/w500${posterPath}"
                }

                Picasso.get().load(imageUrl).into(posterImg)
                movieTitle.text = title
                movieReleaseDate.text = releaseDate
                movieVoteAverage.text = voteAverage.toString()
                movieOverview.text = overview
            }

            itemView.setOnClickListener {
                onMovieClickListener.onMovieClick(movies)
            }

        }


        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
                return MovieViewHolder(view)
            }
        }


    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movies)
    }

}