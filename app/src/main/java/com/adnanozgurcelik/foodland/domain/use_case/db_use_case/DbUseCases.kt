package com.adnanozgurcelik.foodland.domain.use_case.db_use_case

data class DbUseCases(
    val getFavFoods: GetFavFoods,
    val getFavFoodById: GetFavFoodById,
    val insertFavFood: InsertFavFood,
    val deleteFavFood: DeleteFavFood
)