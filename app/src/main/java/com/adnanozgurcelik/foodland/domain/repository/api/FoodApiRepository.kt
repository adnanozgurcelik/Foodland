package com.adnanozgurcelik.foodland.domain.repository.api

import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.FoodInfoDto
import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto

interface FoodApiRepository {
    suspend fun getFoodsBySearch(): SearchDto
    suspend fun getFoodsByCuisine(): SearchDto
    suspend fun getFoodInfo(): FoodInfoDto
}