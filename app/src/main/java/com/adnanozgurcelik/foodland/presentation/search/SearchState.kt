package com.adnanozgurcelik.foodland.presentation.search

import com.adnanozgurcelik.foodland.domain.model.search.Search

data class SearchState(
    val data: List<Search>? = emptyList(),
    val error: String? = "",
    val isLoading: Boolean = false,
    var searchQuery: String? = "",
    val foodId: String? = "",
    val toastMessage: String? = ""
)