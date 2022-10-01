package com.stone.movieapp.data.models

import androidx.lifecycle.LiveData
import com.stone.movieapp.data.vos.*
import com.stone.movieapp.network.dataagents.MovieDataAgent
import com.stone.movieapp.network.dataagents.RetrofitDataAgentImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(),MovieModel {
//    private val mMovieApi: MovieDataAgent = RetrofitDataAgentImpl
//    private var mMoviesDatabase: MoviesDatabase? = null



    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie ->
                    movie.type = NOW_PLAYING
                }
                mMoviesDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
        return mMoviesDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)

  /*      onSuccess(mMoviesDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())
        (onSuccess = {
            it.forEach { movie ->
                movie.type = NOW_PLAYING
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
            }catch (e:Exception){
                Log.i("goooo",e.toString())
            }
            var list= mMoviesDatabase!!.movieDao().getAllMovies()

            Log.i("gooooDB",list.toString())

            onSuccess(it)
        }, onFailure)*/

    }

    override fun getPopularMovies( onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        mMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie ->
                    movie.type = POPULAR
                }
                mMoviesDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

            },{
                onFailure(it.localizedMessage ?: "")
            })

        return mMoviesDatabase?.movieDao()?.getMoviesByType(type = POPULAR)


    /*    onSuccess(mMoviesDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())
        mMovieApi.getPopularMovies(onSuccess = {
            it.forEach { movie ->
                movie.type = POPULAR
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }
            , onFailure) */
    }

    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        mMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie ->
                    movie.type = TOP_RATED
                }

                mMoviesDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMoviesDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED)
   /*     mMovieApi.getTopRatedMovies(onSuccess = {
            it.forEach { movie ->
                movie.type = TOP_RATED
            }

            mMoviesDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure)*/
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        mMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       onSuccess(it.genres ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getMoviesByGenre(genreId=genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
//        mMovieApi.getMoviesByGenre(genreId = genreId, onSuccess, onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
//        mMovieApi.getActors(onSuccess, onFailure)
    }

    override fun getMovieDetail(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO>? {

        mMovieApi.getMovieDetail(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDB= mMoviesDatabase?.movieDao()?.getMoviesByIdOneTime(movieId = movieId.toInt())
                it.type=movieFromDB?.type
                mMoviesDatabase?.movieDao()?.insertSingleMovie(it)

            },{
                onFailure(it.localizedMessage ?: "")
            })

        return mMoviesDatabase?.movieDao()?.getMoviesById(movieId = movieId.toInt())

  /*      val movieFromDB= mMoviesDatabase?.movieDao()?.getMoviesById(movieId = movieId.toInt())
        movieFromDB?.let {
            onSuccess(it)
        }
        mMovieApi.getMovieDetail(movieId, onSuccess = {
           val movieFromDB= mMoviesDatabase?.movieDao()?.getMoviesById(movieId = movieId.toInt())
           it.type=movieFromDB?.type
            mMoviesDatabase?.movieDao()?.insertSingleMovie(it)
            onSuccess(it)
        }, onFailure)*/

    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast ?: listOf(),it.crew ?: listOf()))
            },{
                onFailure(it.localizedMessage ?: "")
            })
//        mMovieApi.getCreditByMovie(movieId, onSuccess, onFailure)
    }
}