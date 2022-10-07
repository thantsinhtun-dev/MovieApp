package com.stone.movieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO

class MainViewModel : ViewModel() {

    private val mMovieModel: MovieModel = MovieModelImpl

    var nowPlayingMovieLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    val genresLiveData = MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorsLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitializeData() {
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    fun getMovieByGenre(position: Int) {
        genresLiveData.value?.getOrNull(position)?.id?.let {
            mMovieModel.getMoviesByGenreId(
                genreId = it.toString(),
                onSuccess = { moviesByGenre ->
                    moviesByGenreLiveData.postValue(moviesByGenre)
                },
                onFailure = { errorMessage ->
                    mErrorLiveData.postValue(errorMessage)
                }
            )
        }
    }

}