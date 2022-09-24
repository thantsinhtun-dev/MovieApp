package com.stone.movieapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.persistance.daos.MovieDao

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MoviesDatabase :RoomDatabase(){

    companion object{
        const val DB_NAME="THE_MOVIE_DB"

        var dbInstance:MoviesDatabase? = null

        fun getMovieDB(context:Context):MoviesDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, MoviesDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }

            }
            return dbInstance
        }
    }
    abstract  fun movieDao():MovieDao
}