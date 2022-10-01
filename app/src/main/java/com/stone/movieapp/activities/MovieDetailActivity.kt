package com.stone.movieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.stone.movieapp.R
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.utils.IMAGE_BASE_URL
import com.stone.movieapp.viewPods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*

private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

class MovieDetailActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    lateinit var actorViewPods: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        //viewpods
        setUpViewPods()
        setUpListener()
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            requestData(it)
        }
    }

    private fun requestData(movieId: Int) {
        mMovieModel.getMovieDetail(
            movieId=movieId.toString(),
            onFailure={
                showError(it)
            }
        )?.observe(this){
            bindData(it)
        }

        mMovieModel.getCreditByMovie(
            movieId.toString(),
            {
                actorViewPods.setNewData(it.first)
                creatorViewPod.setNewData(it.second)
            },
            {
                showError(it)
            })
    }

    private fun setUpListener() {
        btnBack.setOnClickListener { onBackPressed() }
    }

    private fun setUpViewPods() {
        actorViewPods = vpActors as ActorListViewPod
        actorViewPods.setUpActorViewPods(
            R.color.colorPrimary,
            getString(R.string.lbl_actors),
            ""
        )
        creatorViewPod = vpCreators as ActorListViewPod
        creatorViewPod.setUpActorViewPods(
            R.color.colorPrimary,
            getString(R.string.lbl_creators),
            getString(R.string.lbl_more_creator)
        )
    }

    private fun bindData(movieVO: MovieVO) {

        Glide.with(this)
            .load("$IMAGE_BASE_URL${movieVO.posterPath}")
            .into(ivMovie)

        tvMovieName.text = movieVO.title
        tvReleaseYear.text = movieVO.releaseDate?.subSequence(0, 4)
        rbMovieRating.rating = movieVO.getRatingBaseOnFiveStars()
        tvRating.text = movieVO.voteAverage?.toString() ?: ""
        movieVO.voteCount?.let {
            tvNumberOfVotes.text = "$it Votes"
        }

        bindGenres(movieVO, movieVO.genres ?: listOf())

        toolBar.title = movieVO.title ?: ""
        tvOverView.text = movieVO.overview ?: ""
        tvOriginalTitle.text = movieVO.title ?: ""
        tvType.text = movieVO.getGenresAsCommaSeparatedString()
        tvProduction.text = movieVO.getProductCountriesAsCommaSeparatedString()
        tvPremiere.text = movieVO.releaseDate ?: ""
        tvDescription.text = movieVO.overview ?: ""

    }

    private fun bindGenres(movie: MovieVO, genres: List<GenreVO>) {
        movie.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""
            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }

    }

    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}