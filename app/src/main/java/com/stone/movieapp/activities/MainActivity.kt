package com.stone.movieapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.stone.movieapp.R
import com.stone.movieapp.adapter.BannerAdapter
import com.stone.movieapp.adapter.ShowCasesAdapter
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.MovieVO
import com.stone.movieapp.mvp.presenters.MainPresenter
import com.stone.movieapp.mvp.presenters.MainPresenterImpl
import com.stone.movieapp.mvp.views.MainView
import com.stone.movieapp.routers.navigateToMovieDetailsActivity
import com.stone.movieapp.routers.navigateToMovieSearchActivity
import com.stone.movieapp.viewPods.ActorListViewPod
import com.stone.movieapp.viewPods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    //adapter
    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowCasesAdapter: ShowCasesAdapter

    //view pods
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

   //presenter
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
        //App Bar leading Icon
        setUpToolBar()
        setUpViewPod()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()
        setUpListener()


//        requestData()

        mPresenter.onUIReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

  /*  private fun requestData() {
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

    } */

   /* private fun getMovieByGenreId(genreId: Int) {
        movieModel.getMoviesByGenreId(
            genreId = genreId.toString(),
            {
                mMovieByGenreViewPod.setData(it)
            },
            {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()

            }
        )
    }*/

    private fun setUpViewPod() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)

        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpActorList as ActorListViewPod
//        mActorListViewPod.setUpActorViewPods(this)


    }

    private fun setUpShowCaseRecyclerView() {
        mShowCasesAdapter = ShowCasesAdapter(mPresenter)
        rvShowCases.adapter = mShowCasesAdapter
        rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpListener() {
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position ?: 0)
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
        mBannerAdapter = BannerAdapter(mPresenter)
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
//            startActivity(SearchMovieActivity.getInstance(this))
            navigateToMovieSearchActivity()
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onTapMovieFromBanner(movieId: Int) {
//        startActivity(MovieDetailActivity.newIntent(this, movieId))
////        Snackbar.make(window.decorView,"Bannner $movieId",Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onTapMovieFromShowCases(movieId: Int) {
//        startActivity(MovieDetailActivity.newIntent(this, movieId))
////        Snackbar.make(window.decorView,"ShowCases $movieId",Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onTapMovie(movieId: Int) {
//        startActivity(MovieDetailActivity.newIntent(this, movieId))
////        Snackbar.make(window.decorView,"Best Popular movie or movie by genre $movieId",Snackbar.LENGTH_LONG).show()
//    }

    override fun showNowPlayingMovie(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowCasesAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTapLayout(genreList)
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMovieByGenreViewPod.setData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setNewData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int) {
        navigateToMovieDetailsActivity(movieId)
    }

    override fun showError(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

//    private fun showError(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//    }
}