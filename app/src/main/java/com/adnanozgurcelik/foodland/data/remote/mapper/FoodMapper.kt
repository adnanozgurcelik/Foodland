package com.adnanozgurcelik.foodland.data.remote.mapper

import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto
import com.adnanozgurcelik.foodland.domain.model.search.Search

fun SearchDto.toSearch(): List<Search>
= results!!.map { results ->
    Search(
        id = results.id ?: 0,
        image = results.image ?: "",
        title = results.title ?: ""
    )
}
