package com.adnanozgurcelik.foodland.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM fav_food_tbl")
    fun getFavFoods(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM fav_food_tbl WHERE dbId = :dbId")
    suspend fun getFavFoodById(dbId: Int): FoodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavFood(foodEntity: FoodEntity)

    @Delete
    suspend fun deleteFavFood(foodEntity: FoodEntity)
}