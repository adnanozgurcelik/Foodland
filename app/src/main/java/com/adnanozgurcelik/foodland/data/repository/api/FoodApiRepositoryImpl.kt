package com.adnanozgurcelik.foodland.data.repository.api

import com.adnanozgurcelik.foodland.data.remote.FoodApi
import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.FoodInfoDto
import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto
import com.adnanozgurcelik.foodland.domain.repository.api.FoodApiRepository
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor(
    private val api: FoodApi
): FoodApiRepository {
    override suspend fun getFoodsBySearch(query: String): SearchDto
    = api.getFoodsBySearch(query = query)

    override suspend fun getFoodsByCuisine(cuisine: String): SearchDto
    = api.getFoodsByCuisine(cuisine = cuisine)

    override suspend fun getFoodInfo(id: Int): FoodInfoDto
    = api.getFoodInfo(id = id)
}