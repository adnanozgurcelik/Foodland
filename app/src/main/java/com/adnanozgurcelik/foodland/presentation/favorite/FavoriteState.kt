package com.adnanozgurcelik.foodland.presentation.favorite

import com.adnanozgurcelik.foodland.data.local.FoodEntity

data class FavoriteState(
    val data: List<FoodEntity>? = emptyList(),
    val error: String? = "",
    val toastMessage: String? = ""
)
