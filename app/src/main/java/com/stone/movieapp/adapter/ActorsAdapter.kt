package com.stone.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieapp.R
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.viewHolder.ActorsViewHolder

class ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>(){
    var mActorList:List<ActorVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actors,parent,false)
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        if (mActorList.isNotEmpty()){
            holder.bindData(mActorList[position])
        }
    }

    override fun getItemCount(): Int {
        return mActorList.size
    }

    fun setNewDate(actors: List<ActorVO>) {
        mActorList=actors
        notifyDataSetChanged()
    }
}