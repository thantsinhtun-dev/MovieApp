package com.stone.movieapp.interactors

import androidx.lifecycle.LiveData
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

object MovieInteractorImpl : MovieInteractor {

    private val mMovieModel: MovieModel = MovieModelImpl
    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getPopularMovies(onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getTopRatedMovies(onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getGenres(onSuccess, onFailure)
    }

    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieModel.getMoviesByGenreId(genreId,onSuccess,onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieModel.getActors(onSuccess,onFailure)
    }

    override fun getMovieDetail(movieId: String, onFailure: (String) -> Unit): LiveData<MovieVO>? {
        return mMovieModel.getMovieDetail(movieId,onFailure)
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieModel.getCreditByMovie(movieId,onSuccess,onFailure)
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieModel.searchMovie(query)
    }
}