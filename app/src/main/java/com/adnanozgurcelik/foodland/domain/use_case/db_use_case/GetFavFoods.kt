package com.adnanozgurcelik.foodland.domain.use_case.db_use_case

import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.repository.database.FoodDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavFoods @Inject constructor(
    private val repository: FoodDbRepository
) {
    operator fun invoke(): Flow<List<FoodEntity>>
    = repository.getFavFoods()
}