package com.adnanozgurcelik.foodland.domain.repository.database

import com.adnanozgurcelik.foodland.data.local.FoodEntity
import kotlinx.coroutines.flow.Flow

interface FoodDbRepository {
    fun getFavFoods(): Flow<List<FoodEntity>>
    suspend fun getFavFoodById(dbId: Int): FoodEntity?
    suspend fun insertFavFood(foodEntity: FoodEntity)
    suspend fun deleteFavFood(foodEntity: FoodEntity)
}