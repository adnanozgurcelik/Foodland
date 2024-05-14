package com.adnanozgurcelik.foodland.data.remote

import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.FoodInfoDto
import com.adnanozgurcelik.foodland.data.remote.dto.search_dto.SearchDto
import com.adnanozgurcelik.foodland.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodApi {
    @GET("/recipes/complexSearch")
    suspend fun getFoodsBySearch(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("query") query: String
    ): SearchDto

    @GET("/recipes/complexSearch")
    suspend fun getFoodsByCuisine(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("cuisine") cuisine: String
    ): SearchDto

    @GET("/recipes/{id}/information")
    suspend fun getFoodInfo(
        @Query("apiKey") apiKey: String = API_KEY,
        @Path("id") id: String
    ): FoodInfoDto
}