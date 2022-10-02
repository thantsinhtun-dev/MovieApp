package com.stone.movieapp.network

import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.network.responses.GetActorResponse
import com.stone.movieapp.network.responses.GetCreditsByMoviesResponse
import com.stone.movieapp.network.responses.GetGenreResponse
import com.stone.movieapp.network.responses.MovieListResponse
import com.stone.movieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey:String= API_KEY,
        @Query(PARAM_PAGE) page:Int=1
        ):Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey:String= API_KEY,
        @Query(PARAM_PAGE) page:Int=1
    ):Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey:String= API_KEY,
        @Query(PARAM_PAGE) page:Int=1
    ):Observable<MovieListResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
        @Query(PARAM_GENRE_ID) genreId:String
    ):Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
    ):Observable<GetGenreResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
        @Query(PARAM_PAGE) page:Int=1
    ):Observable<GetActorResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId:String,
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
    ):Observable<MovieVO>

    @GET("$API_GET_CREDIT_BY_MOVIE/{movie_id}/credits")
    fun getCreditByMovie(
        @Path("movie_id") movieId:String,
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
    ):Observable<GetCreditsByMoviesResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(PARAM_API_KEY) apiKey: String= API_KEY,
        @Query(PARAM_QUERY) query: String
    ):Observable<MovieListResponse>
}