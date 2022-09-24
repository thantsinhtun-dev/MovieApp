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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl{
//    : MovieDataAgent {
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieTask().execute()
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
//    class GetNowPlayingMovieTask : AsyncTask<Void, Void, MovieListResponse?>() {
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//            val url: URL
//            var reader: BufferedReader? = null
//            val stringBuilder: StringBuilder
//            try {
//                url =
//                    URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$API_KEY&language=en_US&page=1""")
//                val connection = url.openConnection() as HttpURLConnection
//
//                connection.requestMethod = "GET"
//                connection.readTimeout = 15 * 1000
//                connection.doInput = true
//                connection.doOutput = false
//                connection.connect()
//
//                //read
//                reader = BufferedReader(InputStreamReader(connection.inputStream))
//                stringBuilder = StringBuilder()
//                for (line in reader.readLines()){
//                    stringBuilder.append(line+"\n")
//                }
//                val responseString = stringBuilder.toString();
//
//
//                val movieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                )
//                Log.d("GoooNowPlayingMovies",Gson().toJson(movieListResponse))
//                return movieListResponse;
//            } catch (e: Exception) {
////                Log.i("Gooo",e.toString())
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