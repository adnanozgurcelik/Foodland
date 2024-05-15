package com.adnanozgurcelik.foodland.presentation.favorite

import com.adnanozgurcelik.foodland.data.local.FoodEntity

sealed class FavoriteEvent {
    data class DeleteFromFav(val foodEntity: FoodEntity): FavoriteEvent()
}