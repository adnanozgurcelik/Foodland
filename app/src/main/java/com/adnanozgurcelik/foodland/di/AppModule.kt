package com.adnanozgurcelik.foodland.di

import android.app.Application
import androidx.room.Room
import com.adnanozgurcelik.foodland.data.local.FoodDatabase
import com.adnanozgurcelik.foodland.data.remote.FoodApi
import com.adnanozgurcelik.foodland.data.repository.api.FoodApiRepositoryImpl
import com.adnanozgurcelik.foodland.data.repository.database.FoodDbRepositoryImpl
import com.adnanozgurcelik.foodland.domain.repository.api.FoodApiRepository
import com.adnanozgurcelik.foodland.domain.repository.database.FoodDbRepository
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.ApiUseCases
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.GetFoodInfo
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.GetFoodsByCuisine
import com.adnanozgurcelik.foodland.domain.use_case.api_use_case.GetFoodsBySearch
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.DbUseCases
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.DeleteFavFood
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.GetFavFoodById
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.GetFavFoods
import com.adnanozgurcelik.foodland.domain.use_case.db_use_case.InsertFavFood
import com.adnanozgurcelik.foodland.util.Constants.BASE_URL
import com.adnanozgurcelik.foodland.util.Constants.TABLE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiRepository(api: FoodApi)
    = FoodApiRepositoryImpl(api) as FoodApiRepository

    @Provides
    @Singleton
    fun provideDbRepository(db: FoodDatabase)
    = FoodDbRepositoryImpl(db) as FoodDbRepository

    @Provides
    @Singleton
    fun provideRetrofit(): FoodApi
    = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodApi::class.java)

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): FoodDatabase
    = Room.databaseBuilder(
        application,
        FoodDatabase::class.java,
        TABLE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideApiUseCases(repository: FoodApiRepository): ApiUseCases
    = ApiUseCases(
        getFoodsBySearch = GetFoodsBySearch(repository),
        getFoodsByCuisine = GetFoodsByCuisine(repository),
        getFoodInfo = GetFoodInfo(repository)
    )

    @Provides
    @Singleton
    fun provideDbUseCases(repository: FoodDbRepository): DbUseCases
    = DbUseCases(
        getFavFoods = GetFavFoods(repository),
        getFavFoodById = GetFavFoodById(repository),
        insertFavFood = InsertFavFood(repository),
        deleteFavFood = DeleteFavFood(repository)
    )
}