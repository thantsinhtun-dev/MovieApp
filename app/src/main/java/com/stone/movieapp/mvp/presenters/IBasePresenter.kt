package com.stone.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUIReady(owner: LifecycleOwner)
}