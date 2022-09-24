package com.stone.movieapp.persistance.typesconverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(collectionString: String):CollectionVO?{
        val collectionType=object  : TypeToken<CollectionVO>() {}.type
        return Gson().fromJson(collectionString,collectionType)
    }
}