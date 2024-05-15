package com.adnanozgurcelik.foodland.presentation.search

import com.adnanozgurcelik.foodland.data.local.FoodEntity

sealed class SearchEvent {
    data object AddToFav: SearchEvent()
    data class DeleteFromFav(val foodEntity: FoodEntity): SearchEvent()
    data class SearchQueryChange(val query: String): SearchEvent()
}