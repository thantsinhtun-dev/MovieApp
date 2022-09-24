package com.stone.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import com.stone.movieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieName

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mMovieVO: MovieVO? = null
    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage?.toString()
        itemView.rbMovieRating.rating = movie.getRatingBaseOnFiveStars()
    }

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                mDelegate.onTapMovie(movie.id)
            }

        }
    }
}