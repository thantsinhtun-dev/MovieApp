package com.stone.movieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jakewharton.rxbinding4.widget.textChanges
import com.stone.movieapp.R
import com.stone.movieapp.adapter.MovieAdapter
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_movie.*
import java.util.concurrent.TimeUnit

class SearchMovieActivity : AppCompatActivity(),MovieViewHolderDelegate{

    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,SearchMovieActivity::class.java)
        }
    }

    private lateinit var mMovieAdapter: MovieAdapter
    private val mMovieModel:MovieModel = MovieModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        setUpRecyclerView()
        setUpListener()

    }

    private fun setUpListener() {
        ediSearch.textChanges()
            .debounce(1000L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                showError(it.localizedMessage ?: "")
            }
            )

    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        rvMovieList.adapter = mMovieAdapter
    }

    override fun onTapMovie(movieId: Int) {

    }
    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}