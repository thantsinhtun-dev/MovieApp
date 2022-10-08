package com.stone.movieapp.routers

import android.app.Activity
import com.stone.movieapp.activities.MovieDetailActivity
import com.stone.movieapp.activities.SearchMovieActivity

fun Activity.navigateToMovieDetailsActivity(movieId:Int){
    startActivity(MovieDetailActivity.newIntent(this,movieId))
}
fun Activity.navigateToMovieSearchActivity(){
    startActivity(SearchMovieActivity.getInstance(this))
}