package com.adnanozgurcelik.foodland.domain.use_case.db_use_case

import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.repository.database.FoodDbRepository
import javax.inject.Inject

class GetFavFoodById @Inject constructor(
    private val repository: FoodDbRepository
) {
    suspend operator fun invoke(dbId: Int): FoodEntity?
    = repository.getFavFoodById(dbId)
}