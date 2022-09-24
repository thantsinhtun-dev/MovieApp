package com.stone.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_actors.view.*

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actorVO: ActorVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actorVO.profilePath}")
            .into(itemView.ivBestActors)
        itemView.tvActorName.text=actorVO.name
    }
}