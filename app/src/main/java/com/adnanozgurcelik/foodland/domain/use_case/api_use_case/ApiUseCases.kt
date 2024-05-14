package com.adnanozgurcelik.foodland.domain.use_case.api_use_case

data class ApiUseCases(
    val getFoodsBySearch: GetFoodsBySearch,
    val getFoodsByCuisine: GetFoodsByCuisine,
    val getFoodInfo: GetFoodInfo
)
