package com.adnanozgurcelik.foodland.domain.repository.api

import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.FoodInfoDto
import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto

interface FoodApiRepository {
    suspend fun getFoodsBySearch(query: String): SearchDto
    suspend fun getFoodsByCuisine(cuisine: String): SearchDto
    suspend fun getFoodInfo(id: Int): FoodInfoDto
}