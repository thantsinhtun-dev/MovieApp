package com.stone.movieapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.movieapp.data.vos.MovieVO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieVO>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movieVO: MovieVO)

    @Query("Select * from movies")
    fun getAllMovies():LiveData<List<MovieVO>>

    @Query("delete from movies")
    fun deleteAllMovies()

    @Query("Select * from movies where id = :movieId")
    fun getMoviesById(movieId:Int) :LiveData<MovieVO>?

    @Query("Select * from movies where id = :movieId")
    fun getMoviesByIdOneTime(movieId:Int) :MovieVO?


    @Query("Select * from movies where type = :type")
    fun getMoviesByType(type:String) :LiveData<List<MovieVO>>?



}