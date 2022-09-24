package com.stone.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieapp.R
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import com.stone.movieapp.viewHolder.MovieViewHolder

class MovieAdapter(private val mDelegate:MovieViewHolderDelegate): RecyclerView.Adapter<MovieViewHolder>() {
    private var mMovieList : List<MovieVO> = arrayListOf();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie,parent,false)
        return MovieViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
       return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movies: List<MovieVO>) {
        mMovieList=movies
        notifyDataSetChanged()
    }
}