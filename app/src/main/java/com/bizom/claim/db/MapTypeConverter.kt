package com.bizom.claim.db

import androidx.room.TypeConverter
import com.bizom.claim.utils.toTypeToken
import com.google.gson.GsonBuilder

/**
 * Room converter for the [Map] class type.
 */
object MapTypeConverter {

    @TypeConverter
    @JvmStatic
    fun fromMap(map: Map<String, String>): String {
        return GsonBuilder().create().toJson(map, toTypeToken<Map<String, String>>())
    }

    @TypeConverter
    @JvmStatic
    fun toMap(str: String): Map<String, String> {
        return GsonBuilder().create().fromJson(str, toTypeToken<Map<String, String>>())
    }

}