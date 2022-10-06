package com.stone.movieapp.mvp.presenters


import com.stone.movieapp.delegate.BannerViewHolderDelegate
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import com.stone.movieapp.delegate.ShowCasesViewHolderDelegate
import com.stone.movieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowCasesViewHolderDelegate,
    MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}