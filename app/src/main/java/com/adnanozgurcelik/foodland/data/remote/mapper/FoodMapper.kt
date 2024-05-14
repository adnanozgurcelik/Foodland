package com.adnanozgurcelik.foodland.data.remote.mapper

import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.FoodInfoDto
import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto
import com.adnanozgurcelik.foodland.domain.model.info.FoodInfo
import com.adnanozgurcelik.foodland.domain.model.search.Search

fun SearchDto.toSearch(): List<Search>
= results!!.map { results ->
    Search(
        id = results.id ?: 0,
        image = results.image ?: "",
        title = results.title ?: ""
    )
}

fun FoodInfoDto.toFoodInfo(): FoodInfo
= FoodInfo(
    dairyFree = dairyFree ?: false,
    dishTypes = dishTypes ?: emptyList(),
    extendedIngredients = extendedIngredients ?: emptyList(),
    glutenFree = glutenFree ?: false,
    healthScore = healthScore ?: healthScore,
    id = id ?: 0,
    image = image ?: "",
    instructions = instructions ?: "",
    readyInMinutes = readyInMinutes ?: 0,
    servings = servings ?: 0,
    score = score ?: 0.0,
    summary = summary ?: "",
    title = title ?: "",
    vegan = vegan ?: false,
    vegetarian = vegetarian ?: false,
)
