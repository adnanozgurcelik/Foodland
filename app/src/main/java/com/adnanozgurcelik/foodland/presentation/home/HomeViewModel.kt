package com.adnanozgurcelik.foodland.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.data.mapper.toFoodEntity
import com.adnanozgurcelik.foodland.domain.model.search.Search
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.ApiUseCases
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.DbUseCases
import com.adnanozgurcelik.foodland.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    private val dbUseCases: DbUseCases
): ViewModel() {

    var state by mutableStateOf(HomeState())
    private val mainFoods = listOf(
        "pasta", "pizza", "chicken", "beef", "pork", "sandwich", "rice", "fish"
    )
    private var selectRandom = mainFoods.random()
    private var recentlyDeletedFood: FoodEntity? = null

    init {
        getRecommendedFoods()
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.AddToFav -> {
                addToFav(id = state.foodId ?: 633376)
            }
            is HomeEvent.DeleteFromFav -> {
                deleteFromFav(event.foodEntity)
            }
        }
    }

    private fun getRecommendedFoods() {
        viewModelScope.launch {
            apiUseCases.getFoodsBySearch.invoke(selectRandom)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { foods ->
                                state = state.copy(
                                    data = foods,
                                    foodId = foods[0].id
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }

    private fun addToFav(id: Int) {
        try {
            viewModelScope.launch {
                apiUseCases.getFoodInfo.invoke(id)
                    .collect { result ->
                        when(result) {
                            is Resource.Success -> {
                                try {
                                    val fromRemote = result.data?.toFoodEntity() ?: return@collect
                                    withContext(Dispatchers.IO) {
                                        dbUseCases.insertFavFood.invoke(fromRemote)
                                        state = state.copy(
                                            toastMessage = "Food has been saved"
                                        )
                                    }
                                } catch (e: Exception) {
                                    state = state.copy(
                                        toastMessage = e.localizedMessage ?: "Not saved"
                                    )
                                }
                            }
                            is Resource.Error -> {
                                state = state.copy(
                                    toastMessage = result.message ?: "An unexpected error occurred"
                                )
                            }
                            is Resource.Loading -> Unit
                        }
                    }
            }
        } catch (e: Exception) {
            state = state.copy(
                error = "Error: ${e.localizedMessage}. Cause: ${e.cause}."
            )
        }
    }

    private fun deleteFromFav(foodEntity: FoodEntity) {
        try {
            viewModelScope.launch {
                dbUseCases.deleteFavFood.invoke(foodEntity)
                recentlyDeletedFood = foodEntity
            }
        } catch (e: Exception) {
            state = state.copy(
                toastMessage = e.localizedMessage ?: "Not deleted"
            )
        }
    }
}