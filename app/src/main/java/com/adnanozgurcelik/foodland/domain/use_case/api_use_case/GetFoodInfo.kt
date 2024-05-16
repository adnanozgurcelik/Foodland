package com.adnanozgurcelik.foodland.domain.use_case.api_use_case

import com.adnanozgurcelik.foodland.data.mapper.toFoodInfo
import com.adnanozgurcelik.foodland.domain.model.info.FoodInfo
import com.adnanozgurcelik.foodland.domain.repository.api.FoodApiRepository
import com.adnanozgurcelik.foodland.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodInfo @Inject constructor(
    private val repository: FoodApiRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<FoodInfo>>
    = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getFoodInfo(id)
            val result = response.toFoodInfo()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}. Cause: ${e.cause}."))
        }
    }
}