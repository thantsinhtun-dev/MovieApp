package com.stone.movieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.network.responses.MovieListResponse
import com.stone.movieapp.utils.API_GET_NOW_PLAYING
import com.stone.movieapp.utils.API_KEY
import com.stone.movieapp.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

object OkhttpDataAgentImpl  {
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieOkHttpTask(mClient).execute();
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getMoviesByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getMovieDetail(
//        movieId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    private val mClient = OkHttpClient.Builder()
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build()
//
//    class GetNowPlayingMovieOkHttpTask(private val client: OkHttpClient) :
//        AsyncTask<Void, Void, MovieListResponse>() {
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//            val request = Request.Builder()
//                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$API_KEY&language=en_US&page=1""")
//                .build()
//            try {
//                val response = client.newCall(request).execute()
//                if (response.isSuccessful) {
//                    response.body?.let {
//                        val responseString = it.string()
//                        val responseData = Gson().fromJson<MovieListResponse>(
//                            responseString,
//                            MovieListResponse::class.java
//                        )
//                        Log.i("GoooOkhttp",Gson().toJson(responseData))
//                        return responseData;
//
//                    }
//                }
//            }catch (e:Exception){
//
//            }
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }

}