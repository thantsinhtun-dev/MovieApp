package com.stone.movieapp

import android.app.Application
import com.stone.movieapp.data.models.MovieModelImpl

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}