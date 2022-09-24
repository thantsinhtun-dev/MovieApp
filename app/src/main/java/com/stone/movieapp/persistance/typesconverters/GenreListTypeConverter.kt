package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.GenreVO

class GenreListTypeConverter {
        @TypeConverter
        fun toString(collection: List<GenreVO>?):String{
            return Gson().toJson(collection)
        }

        @TypeConverter
        fun toCollectionVO(genreIdString: String): List<GenreVO>?{
            val collectionType=object  : TypeToken<List<GenreVO>>() {}.type
            return Gson().fromJson(genreIdString,collectionType)

    }
}