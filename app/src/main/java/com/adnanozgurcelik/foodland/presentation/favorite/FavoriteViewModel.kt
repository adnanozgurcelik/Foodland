package com.adnanozgurcelik.foodland.presentation.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.DbUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val dbUseCases: DbUseCases
): ViewModel() {

    var state by mutableStateOf(FavoriteState())
    private var recentlyDeletedFood: FoodEntity? = null

    init {
        getFavFoods()
    }

    fun onEvent(event: FavoriteEvent) {
        when(event) {
            is FavoriteEvent.DeleteFromFav -> {
                deleteFromFav(event.foodEntity)
            }
        }
    }

    private fun getFavFoods() {
        try {
            viewModelScope.launch {
                dbUseCases.getFavFoods.invoke()
                    .collect { favFoods ->
                        state = state.copy(
                            data = favFoods
                        )
                    }
            }
        } catch (e: Exception) {
            state = state.copy(
                error = "Error!"
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