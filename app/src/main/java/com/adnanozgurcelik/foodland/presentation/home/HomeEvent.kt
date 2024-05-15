package com.adnanozgurcelik.foodland.presentation.home

import androidx.compose.ui.focus.FocusState
import com.adnanozgurcelik.foodland.data.local.FoodEntity

sealed class HomeEvent {
    data object AddToFav: HomeEvent()
    data class DeleteFromFav(val foodEntity: FoodEntity): HomeEvent()
}