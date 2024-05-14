package com.adnanozgurcelik.foodland.data.remote.dto.info_dto

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)