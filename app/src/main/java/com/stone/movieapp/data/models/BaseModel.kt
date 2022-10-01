package com.stone.movieapp.data.models

import android.content.Context
import com.stone.movieapp.network.TheMovieApi
import com.stone.movieapp.network.dataagents.RetrofitDataAgentImpl
import com.stone.movieapp.persistance.MoviesDatabase
import com.stone.movieapp.utils.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mMovieApi: TheMovieApi
    protected var mMoviesDatabase: MoviesDatabase? = null

    init {
        val mClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
       mMovieApi = retrofit.create(TheMovieApi::class.java)
    }
    fun initDatabase(context: Context){
        mMoviesDatabase = MoviesDatabase.getMovieDB(context)
    }

}