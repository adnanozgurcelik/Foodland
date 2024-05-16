package com.adnanozgurcelik.foodland.presentation.detail

import com.adnanozgurcelik.foodland.data.local.FoodEntity

sealed class DetailEvent {
    data class DeleteFromFav(val foodEntity: FoodEntity): DetailEvent()
    data object AddToFav: DetailEvent()
}