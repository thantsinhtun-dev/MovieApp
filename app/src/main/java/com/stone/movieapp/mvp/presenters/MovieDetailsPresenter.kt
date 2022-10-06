package com.stone.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.stone.movieapp.mvp.views.MovieDetailsView

interface MovieDetailsPresenter : IBasePresenter {
    fun initView(view: MovieDetailsView)
    fun onUIReadyInMovieDetails(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}