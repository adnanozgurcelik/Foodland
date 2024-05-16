package com.adnanozgurcelik.foodland.presentation.detail

import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.model.info.FoodInfo

data class DetailState(
    val dataFromApi: FoodInfo? = null,
    val isLoading: Boolean = true,
    val error: String? = "",
    val toastMessage: String? = ""
)
