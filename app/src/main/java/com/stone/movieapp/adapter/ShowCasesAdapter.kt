package com.stone.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieapp.R
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.ShowCasesViewHolderDelegate
import com.stone.movieapp.viewHolder.ShowCasesViewHolder

class ShowCasesAdapter(private val mDelegate: ShowCasesViewHolderDelegate) :
    RecyclerView.Adapter<ShowCasesViewHolder>() {
    private var movieList: List<MovieVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCasesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_show_cases, parent, false)
        return ShowCasesViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCasesViewHolder, position: Int) {
        if (movieList.isNotEmpty()){
            holder.bindData(movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movies: List<MovieVO>) {
        movieList=movies
        notifyDataSetChanged()
    }
}