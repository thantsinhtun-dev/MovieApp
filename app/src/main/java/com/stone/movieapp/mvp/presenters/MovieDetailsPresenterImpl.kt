package com.stone.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.mvp.views.MainView
import com.stone.movieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl : ViewModel(), MovieDetailsPresenter {
    //view
    var mView: MovieDetailsView? = null

    //model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUIReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        mMovieModel.getMovieDetail(movieId = movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            mView?.showMovieDetails(it)
        }

        mMovieModel.getCreditByMovie(
            movieId=movieId.toString(),
            onSuccess = {
                mView?.showCreditsByMovie(cast = it.first, crew = it.second)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUIReady(owner: LifecycleOwner) {

    }
}