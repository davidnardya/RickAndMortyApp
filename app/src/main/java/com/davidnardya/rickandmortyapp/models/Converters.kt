package com.davidnardya.rickandmortyapp.models

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun toStringFromLocation(location: Location?): String? {
        val gson = Gson()
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocationFromString(jsonString: String?): Location? {
        val location: Type? = object : TypeToken<Location?>() {}.type
        return Gson().fromJson<Location>(jsonString, location)
    }

    @TypeConverter
    fun toStringFromOrigin(origin: Origin?): String? {
        val gson = Gson()
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toOriginFromString(jsonString: String?): Origin? {
        val origin: Type? = object : TypeToken<Origin?>() {}.type
        return Gson().fromJson<Origin>(jsonString, origin)
    }
}