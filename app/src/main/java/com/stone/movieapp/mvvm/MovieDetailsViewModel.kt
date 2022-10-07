package com.stone.movieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.MovieVO

class MovieDetailsViewModel : ViewModel() {

    private val mMovieModel: MovieModel = MovieModelImpl

    var movieDetailsLiveData: LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int) {
        movieDetailsLiveData = mMovieModel.getMovieDetail(
            movieId = movieId.toString()
        ) { mErrorLiveData.postValue(it) }

        mMovieModel.getCreditByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                castLiveData.postValue(it.first ?: listOf())
                crewLiveData.postValue(it.second ?: listOf())
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }
}