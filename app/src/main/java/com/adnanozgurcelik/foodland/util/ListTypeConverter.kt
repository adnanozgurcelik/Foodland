package com.adnanozgurcelik.foodland.util

import androidx.room.TypeConverter

class ListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String>
    = value.split(",")

    @TypeConverter
    fun toString(list: List<String>): String
    = list.joinToString(",")
}