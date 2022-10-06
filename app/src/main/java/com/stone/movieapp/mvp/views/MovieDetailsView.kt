package com.stone.movieapp.mvp.views

import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.MovieVO

interface MovieDetailsView : BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}