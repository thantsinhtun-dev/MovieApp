package com.stone.movieapp.viewPods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.movieapp.adapter.ActorsAdapter
import com.stone.movieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var actorsAdapter: ActorsAdapter
    override fun onFinishInflate() {
        setUpActorRecyclerView()
        super.onFinishInflate()
    }

    private fun setUpActorRecyclerView() {
        actorsAdapter = ActorsAdapter()
        rvBestActors.adapter = actorsAdapter
        rvBestActors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setUpActorViewPods(backgroundColor: Int, title: String, moreTitle: String) {
        setBackgroundColor(ContextCompat.getColor(context,backgroundColor));
        tvBestActors.text = title
        tvMoreActors.text = moreTitle
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    fun setNewData(actors: List<ActorVO>) {
        actorsAdapter.setNewDate(actors)
    }
}