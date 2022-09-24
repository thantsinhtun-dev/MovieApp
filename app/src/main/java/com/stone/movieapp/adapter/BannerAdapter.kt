package com.stone.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieapp.R
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.BannerViewHolderDelegate
import com.stone.movieapp.viewHolder.BannerViewHolder

class BannerAdapter(val mDelegate: BannerViewHolderDelegate) : RecyclerView.Adapter<BannerViewHolder>() {
    private var mMovieList:List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_banner, parent, false)
        return BannerViewHolder(view,mDelegate)
    }

    fun setNewData(movieList:List<MovieVO>){
        mMovieList=movieList
        notifyDataSetChanged()
        Log.i("GoooAdapter",movieList.toString())
    }
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            val movie= mMovieList[position]
            Log.i("GoooMovie",movie.toString())
            holder.bindData(movie)
        }
    }

    override fun getItemCount(): Int {
       return if (mMovieList.count()>5){
            5
        }else{
            mMovieList.count()
        }
    }
}