package com.stone.movieapp.viewPods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.movieapp.adapter.MovieAdapter
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import com.stone.movieapp.delegate.ShowCasesViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movies_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter : MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate
    override fun onFinishInflate() {
//        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

   private  fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
    private fun setDelegate(delegate: MovieViewHolderDelegate){
        mDelegate = delegate
    }
    fun setUpMovieListViewPod(delegate:MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    fun setData(movies:List<MovieVO>){
        mMovieAdapter.setNewData(movies);
    }




}