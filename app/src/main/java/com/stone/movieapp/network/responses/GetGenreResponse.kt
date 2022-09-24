package com.stone.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieapp.data.vos.GenreVO

data class GetGenreResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>?
)