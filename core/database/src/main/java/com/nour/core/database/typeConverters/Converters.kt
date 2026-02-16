package com.nour.core.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringToIntMap(value: String): Map<Int, String> {
        val mapType = object : TypeToken<Map<Int, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromIntMapToString(map: Map<Int, String>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun fromStringToStringMap(value: String): Map<String, String> {
        val mapType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMapToString(map: Map<String, String>): String {
        return Gson().toJson(map)
    }
}