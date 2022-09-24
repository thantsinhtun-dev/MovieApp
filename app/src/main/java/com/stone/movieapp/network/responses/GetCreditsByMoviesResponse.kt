package com.stone.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieapp.data.vos.ActorVO

data class GetCreditsByMoviesResponse(
    @SerializedName("cast")
    val cast:List<ActorVO>?,
    @SerializedName("crew")
    val crew:List<ActorVO>?,

)
