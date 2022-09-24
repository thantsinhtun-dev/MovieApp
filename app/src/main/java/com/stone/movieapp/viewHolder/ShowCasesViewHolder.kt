package com.stone.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.ShowCasesViewHolderDelegate
import com.stone.movieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_show_cases.view.*

class ShowCasesViewHolder(itemView: View,private val mDelegate: ShowCasesViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mMovieVO: MovieVO? = null
    fun bindData(movie: MovieVO) {
        mMovieVO=movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCases)

        itemView.tvShowCaseMovieName.text=movie.title
        itemView.tvShowCaseMovieDate.text=movie.releaseDate

    }

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {movie->
                mDelegate.onTapMovieFromShowCases(movie.id)
            }

        }
        itemView.btnPlay.setOnClickListener{
            mMovieVO?.let {movie->
                mDelegate.onTapMovieFromShowCases(movie.id)
            }
        }
    }
}