package com.adnanozgurcelik.foodland.data.mapper

import com.adnanozgurcelik.foodland.data.local.FoodEntity
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
    healthScore = healthScore ?: 0,
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

fun FoodInfo.toFoodEntity(): FoodEntity
= FoodEntity(
    dairyFree = dairyFree,
    dishTypes = dishTypes,
    glutenFree = glutenFree,
    healthScore = healthScore,
    image = image,
    instructions = instructions,
    readyInMinutes = readyInMinutes,
    servings = servings,
    score = score,
    summary = summary,
    title = title,
    vegan = vegan,
    vegetarian = vegetarian,
)
