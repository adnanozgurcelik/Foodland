package com.adnanozgurcelik.foodland.presentation.home

import com.adnanozgurcelik.foodland.domain.model.info.FoodInfo
import com.adnanozgurcelik.foodland.domain.model.search.Search

data class HomeState(
    val data: List<Search>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val toastMessage: String? = "",
    val foodId: String? = ""
)
