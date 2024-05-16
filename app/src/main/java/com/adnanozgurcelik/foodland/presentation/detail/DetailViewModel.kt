package com.adnanozgurcelik.foodland.presentation.detail

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
import com.adnanozgurcelik.foodland.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    private val dbUseCases: DbUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(DetailState())
    private var recentlyDeletedFood: FoodEntity? = null

    init {
        val id = savedStateHandle.get<String>("id") ?: "633376"
        getFoodInfo(id)
    }

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.AddToFav -> {
                addToFav()
            }
            is DetailEvent.DeleteFromFav -> {
                deleteFromFav(event.foodEntity)
            }
        }
    }

    private fun getFoodInfo(id: String) {
        viewModelScope.launch {
            apiUseCases.getFoodInfo.invoke(id)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { foodInfo ->
                                state = state.copy(
                                    dataFromApi = foodInfo
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

    private fun addToFav() {
        try {
            viewModelScope.launch {
                val favFood = state.dataFromApi!!.toFoodEntity()
                dbUseCases.insertFavFood.invoke(favFood)
                state = state.copy(
                    toastMessage = "Food has been saved"
                )
            }
        } catch (e: Exception) {
            state = state.copy(
                error = e.localizedMessage,
                toastMessage = "Not saved"
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