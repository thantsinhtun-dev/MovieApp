package com.stone.movieapp.network.dataagents

import android.util.Log
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.network.TheMovieApi
import com.stone.movieapp.network.responses.GetActorResponse
import com.stone.movieapp.network.responses.GetCreditsByMoviesResponse
import com.stone.movieapp.network.responses.GetGenreResponse
import com.stone.movieapp.network.responses.MovieListResponse
import com.stone.movieapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieDataAgent {
//    private var mTheMovieApi: TheMovieApi? = null
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
////                 response.body()?.let {
//                    val movieList = response.body()?.results ?: listOf()
//                    Log.i("GoooRetrofit",movieList.toString())
//                    onSuccess(movieList);
////                     Log.i("GoooRetrofit",Gson().toJson(it))
////                 }
//                }else(
//                        onFailure(response.message())
//                )
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getPopularMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList);
//                }else {
//                    Log.i("Gooo", response.message())
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                Log.i("Gooo",t.message.toString())
//                onFailure(t.message ?: "")
//
//            }
//        })
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getTopRatedMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList);
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getGenres()?.enqueue(object : Callback<GetGenreResponse> {
//            override fun onResponse(
//                call: Call<GetGenreResponse>,
//                response: Response<GetGenreResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.genres ?: listOf()
//                    onSuccess(movieList);
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getMoviesByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMoviesByGenre(genreId = genreId)?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList);
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getActors()?.enqueue(object : Callback<GetActorResponse> {
//            override fun onResponse(
//                call: Call<GetActorResponse>,
//                response: Response<GetActorResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList);
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<GetActorResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getMovieDetail(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMovieDetail(movieId)?.enqueue(object : Callback<MovieVO> {
//            override fun onResponse(
//                call: Call<MovieVO>,
//                response: Response<MovieVO>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        onSuccess(it)
//                    }
//
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }
//
//    override fun getCreditByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getCreditByMovie(movieId)?.enqueue(object : Callback<GetCreditsByMoviesResponse> {
//            override fun onResponse(
//                call: Call<GetCreditsByMoviesResponse>,
//                response: Response<GetCreditsByMoviesResponse>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                       onSuccess(Pair(it.cast ?: listOf(),it.crew ?: listOf()))
//                    }
//
//                }else(
//                        onFailure(response.message())
//                        )
//            }
//
//            override fun onFailure(call: Call<GetCreditsByMoviesResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        })
//    }

//    init {
//        val mClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
//    }

}