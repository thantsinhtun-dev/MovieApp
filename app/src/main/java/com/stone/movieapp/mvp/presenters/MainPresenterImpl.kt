package com.stone.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.interactors.MovieInteractor
import com.stone.movieapp.interactors.MovieInteractorImpl
import com.stone.movieapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(),MainPresenter{

    //view
    var mView : MainView? = null

    //model
//    private val mMovieModel: MovieModel = MovieModelImpl

    private val mMovieInteractor : MovieInteractor = MovieInteractorImpl


    //states
    private var mGenres: List<GenreVO>? = listOf()


    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId->
            mMovieInteractor.getMoviesByGenreId(
                genreId = genreId.toString(),
                onSuccess = {
                    mView?.showMoviesByGenre(it)
                },
                onFailure = {
                    mView?.showError(it)
                }

            )

        }
    }

    override fun onUIReady(owner: LifecycleOwner) {
        //now playing
        mMovieInteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovie(it)
        }

        //popular
        mMovieInteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }

        //top rated
        mMovieInteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        //genres
        mMovieInteractor.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId->
                    onTapGenre(firstGenreId)
                }
            },
            onFailure = {
                mView?.showError(it)
            }

        )

        mMovieInteractor.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }

        )

    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowCases(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }
}