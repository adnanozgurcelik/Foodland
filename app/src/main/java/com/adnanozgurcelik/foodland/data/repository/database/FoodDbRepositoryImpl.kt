package com.adnanozgurcelik.foodland.data.repository.database

import com.adnanozgurcelik.foodland.data.local.FoodDatabase
import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.repository.database.FoodDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodDbRepositoryImpl @Inject constructor(
    private val db: FoodDatabase
): FoodDbRepository {

    val dao = db.foodDao()

    override fun getFavFoods(): Flow<List<FoodEntity>>
    = dao.getFavFoods()

    override suspend fun getFavFoodById(dbId: Int): FoodEntity?
    = dao.getFavFoodById(dbId)

    override suspend fun insertFavFood(foodEntity: FoodEntity)
    = dao.insertFavFood(foodEntity)

    override suspend fun deleteFavFood(foodEntity: FoodEntity)
    = dao.deleteFavFood(foodEntity)
}