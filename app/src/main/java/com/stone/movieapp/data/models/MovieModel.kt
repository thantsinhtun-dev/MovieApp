package com.stone.movieapp.data.models

import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO

interface MovieModel {
    fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getPopularMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getTopRatedMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getGenres(
        onSuccess:(List<GenreVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getMoviesByGenreId(
        genreId:String,
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getActors(
        onSuccess:(List<ActorVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun getMovieDetail(
        movieId:String,
        onSuccess:(MovieVO)->Unit,
        onFailure:(String)->Unit
    )
    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )
}