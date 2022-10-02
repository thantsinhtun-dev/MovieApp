package com.stone.movieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.stone.movieapp.R
import com.stone.movieapp.adapter.BannerAdapter
import com.stone.movieapp.adapter.ShowCasesAdapter
import com.stone.movieapp.data.models.MovieModel
import com.stone.movieapp.data.models.MovieModelImpl
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.delegate.BannerViewHolderDelegate
import com.stone.movieapp.delegate.MovieViewHolderDelegate
import com.stone.movieapp.delegate.ShowCasesViewHolderDelegate
import com.stone.movieapp.dummy.dummyGenreList
import com.stone.movieapp.network.dataagents.MovieDataAgentImpl
import com.stone.movieapp.network.dataagents.OkhttpDataAgentImpl
import com.stone.movieapp.network.dataagents.RetrofitDataAgentImpl
import com.stone.movieapp.viewPods.ActorListViewPod
import com.stone.movieapp.viewPods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowCasesViewHolderDelegate,
    MovieViewHolderDelegate {
    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowCasesAdapter: ShowCasesAdapter
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    private val movieModel: MovieModel = MovieModelImpl

    private var mGenres: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //App Bar leading Icon
        setUpToolBar()
        setUpViewPod()
        setUpBannerViewPager()
//        setUpGenreTapLayout()
        setUpShowCaseRecyclerView()
        setUpListener()

//        MovieDataAgentImpl.getNowPlayingMovies()
//        OkhttpDataAgentImpl().getNowPlayingMovies()
//        RetrofitDataAgentImpl().getNowPlayingMovies()

        requestData()

    }

    private fun requestData() {
        movieModel.getNowPlayingMovies {
            showError(it)
        }?.observe(this) {
            mBannerAdapter.setNewData(it)
        }


        movieModel.getPopularMovies {
            showError(it)
        }?.observe(this) {
            mBestPopularMovieListViewPod.setData(it)
        }


        movieModel.getTopRatedMovies {
            showError(it)
        }?.observe(this) {
            mShowCasesAdapter.setNewData(it)
        }


        movieModel.getGenres(
            {
                mGenres = it
                setUpGenreTapLayout(it)

                it.firstOrNull()?.id?.let { genreId ->
                    getMovieByGenreId(genreId)
                }
            }, {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        )
        movieModel.getActors(
            {
                mActorListViewPod.setNewData(it)
            }, {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        )

    }

    private fun getMovieByGenreId(genreId: Int) {
        movieModel.getMoviesByGenreId(
            genreId = genreId.toString(),
            {
                mMovieByGenreViewPod.setData(it)
            },
            {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()

            }
        )
    }

    private fun setUpViewPod() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorList as ActorListViewPod
//        mActorListViewPod.setUpActorViewPods(this)


    }

    private fun setUpShowCaseRecyclerView() {
        mShowCasesAdapter = ShowCasesAdapter(this)
        rvShowCases.adapter = mShowCasesAdapter
        rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpListener() {
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMovieByGenreId(it)
                }
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpGenreTapLayout(genres: List<GenreVO>) {

        genres.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPager.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPager)
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var homeAsUpIndicator = supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.search){
            startActivity(SearchMovieActivity.getInstance(this))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
//        Snackbar.make(window.decorView,"Bannner $movieId",Snackbar.LENGTH_LONG).show()
    }

    override fun onTapMovieFromShowCases(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
//        Snackbar.make(window.decorView,"ShowCases $movieId",Snackbar.LENGTH_LONG).show()
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
//        Snackbar.make(window.decorView,"Best Popular movie or movie by genre $movieId",Snackbar.LENGTH_LONG).show()
    }

    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}