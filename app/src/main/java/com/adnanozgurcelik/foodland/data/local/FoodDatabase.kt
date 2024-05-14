package com.adnanozgurcelik.foodland.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adnanozgurcelik.foodland.util.ListTypeConverter

@Database(entities = [FoodEntity::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class FoodDatabase: RoomDatabase() {
    abstract fun foodDao(): FoodDao
}