package com.stone.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieapp.data.vos.ActorVO
import com.stone.movieapp.data.vos.DateVO
import com.stone.movieapp.data.vos.MovieVO

data class GetActorResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<ActorVO>?,
)