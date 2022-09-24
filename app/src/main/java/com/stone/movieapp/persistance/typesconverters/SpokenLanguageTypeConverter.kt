package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.ProductionCountriesVO
import com.stone.movieapp.data.vos.SpokenLanguagesVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(collection: List<SpokenLanguagesVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(genreIdString: String): List<SpokenLanguagesVO>?{
        val collectionType=object  : TypeToken<List<SpokenLanguagesVO>>() {}.type
        return Gson().fromJson(genreIdString,collectionType)
    }
}