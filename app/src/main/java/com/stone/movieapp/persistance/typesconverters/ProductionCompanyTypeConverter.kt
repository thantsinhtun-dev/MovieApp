package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.GenreVO
import com.stone.movieapp.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {


            @TypeConverter
            fun toString(collection: List<ProductionCompaniesVO>?):String{
                return Gson().toJson(collection)
            }

            @TypeConverter
            fun toCollectionVO(genreIdString: String): List<ProductionCompaniesVO>?{
                val collectionType=object  : TypeToken<List<ProductionCompaniesVO>>() {}.type
                return Gson().fromJson(genreIdString,collectionType)
            }

}