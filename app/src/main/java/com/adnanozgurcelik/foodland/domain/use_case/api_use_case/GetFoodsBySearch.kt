package com.adnanozgurcelik.foodland.domain.use_case.api_use_case

import com.adnanozgurcelik.foodland.data.remote.mapper.toSearch
import com.adnanozgurcelik.foodland.domain.model.search.Search
import com.adnanozgurcelik.foodland.domain.repository.api.FoodApiRepository
import com.adnanozgurcelik.foodland.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodsBySearch @Inject constructor(
    private val repository: FoodApiRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<Search>>>
    = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getFoodsBySearch(query)
            val result = response.toSearch()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}. Cause: ${e.cause}."))
        }
    }
}