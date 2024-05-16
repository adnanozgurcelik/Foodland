package com.adnanozgurcelik.foodland.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.data.mapper.toFoodEntity
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.ApiUseCases
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.DbUseCases
import com.adnanozgurcelik.foodland.presentation.home.HomeEvent
import com.adnanozgurcelik.foodland.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    private val dbUseCases: DbUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(SearchState())
    private var recentlyDeletedFood: FoodEntity? = null
    private var job: Job? = null

    init {
        getFoodsBySearch()
    }

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.SearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500L)
                    getFoodsBySearch()
                }
            }
            is SearchEvent.AddToFav -> {
                addToFav(id = state.foodId ?: "")
            }
            is SearchEvent.DeleteFromFav -> {

            }
        }
    }

    private fun getFoodsBySearch(
        query: String = state.searchQuery!!.lowercase()
    ) {
        viewModelScope.launch {
            apiUseCases.getFoodsBySearch.invoke(query)
                .collect { result ->
                    when(result) {
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            result.data?.let { searches ->
                                state = state.copy(
                                    data = searches,
                                    foodId = searches[0].id.toString()
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
        }
    }

    private fun addToFav(id: String) {
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
                            is Resource.Loading -> {
                                state = state.copy(
                                    isLoading = true
                                )
                            }
                            is Resource.Error -> {
                                state = state.copy(
                                    error = result.message
                                )
                            }
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