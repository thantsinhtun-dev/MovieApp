package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.ProductionCompaniesVO
import com.stone.movieapp.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {

    @TypeConverter
    fun toString(collection: List<ProductionCountriesVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(genreIdString: String): List<ProductionCountriesVO>?{
        val collectionType=object  : TypeToken<List<ProductionCountriesVO>>() {}.type
        return Gson().fromJson(genreIdString,collectionType)
    }
}