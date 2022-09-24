package com.stone.movieapp.data.models

import android.content.Context
import android.util.Log
import com.stone.movieapp.data.vos.*
import com.stone.movieapp.network.dataagents.MovieDataAgent
import com.stone.movieapp.network.dataagents.RetrofitDataAgentImpl
import com.stone.movieapp.persistance.MoviesDatabase

object MovieModelImpl : MovieModel {
    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
    private var mMoviesDatabase: MoviesDatabase? = null

    fun initDatabase(context: Context) {
        mMoviesDatabase = MoviesDatabase.getMovieDB(context)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        onSuccess(mMoviesDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())
        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
            it.forEach { movie ->
                movie.type = NOW_PLAYING
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
//            }catch (e:Exception){
//                Log.i("goooo",e.toString())
//            }
//            var list= mMoviesDatabase!!.movieDao().getAllMovies()
//
//            Log.i("gooooDB",list.toString())

            onSuccess(it)
        }, onFailure)

    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        onSuccess(mMoviesDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())
        mMovieDataAgent.getPopularMovies(onSuccess = {
            it.forEach { movie ->
                movie.type = POPULAR
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }
            , onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMoviesDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())
        mMovieDataAgent.getTopRatedMovies(onSuccess = {
            it.forEach { movie ->
                movie.type = TOP_RATED
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        mMovieDataAgent.getGenres(onSuccess, onFailure)
    }

    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(genreId = genreId, onSuccess, onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getActors(onSuccess, onFailure)
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val movieFromDB= mMoviesDatabase?.movieDao()?.getMoviesById(movieId = movieId.toInt())
        movieFromDB?.let {
            onSuccess(it)
        }
        mMovieDataAgent.getMovieDetail(movieId, onSuccess = {
           val movieFromDB= mMoviesDatabase?.movieDao()?.getMoviesById(movieId = movieId.toInt())
           it.type=movieFromDB?.type
            mMoviesDatabase?.movieDao()?.insertSingleMovie(it)
            onSuccess(it)
        }, onFailure)

    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditByMovie(movieId, onSuccess, onFailure)
    }
}