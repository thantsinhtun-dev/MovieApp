package com.stone.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieapp.data.vos.DateVO
import com.stone.movieapp.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("results")
    val results: List<MovieVO>?,

)