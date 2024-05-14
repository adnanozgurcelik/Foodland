package com.adnanozgurcelik.foodland.domain.model.info

import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.ExtendedIngredient

data class FoodInfo(
    val dairyFree: Boolean?,
    val dishTypes: List<String>?,
    val extendedIngredients: List<ExtendedIngredient>?,
    val glutenFree: Boolean?,
    val healthScore: Int?,
    val id: Int?,
    val image: String?,
    val instructions: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val score: Double?,
    val summary: String?,
    val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?
)
