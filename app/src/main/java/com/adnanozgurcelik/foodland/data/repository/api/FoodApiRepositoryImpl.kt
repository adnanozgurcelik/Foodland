package com.adnanozgurcelik.foodland.data.repository.api

import com.adnanozgurcelik.foodland.data.remote.FoodApi
import com.adnanozgurcelik.foodland.domain.repository.api.FoodApiRepository
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor(
    private val api: FoodApi
): FoodApiRepository {
}