package com.adnanozgurcelik.foodland.data.remote.dto.search_dto

data class SearchDto(
    val number: Int?,
    val offset: Int?,
    val results: List<SearchInfoDto>?,
    val totalResults: Int?
)