package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.CollectionVO
import com.stone.movieapp.data.vos.GenreVO

class GenreIdTypeConverter {
    @TypeConverter
    fun toString(collection: List<Int>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(genreIdString: String): List<Int>?{
        val collectionType=object  : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(genreIdString,collectionType)
    }
}