package com.adnanozgurcelik.foodland.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adnanozgurcelik.foodland.data.remote.dto.info_dto.ExtendedIngredient
import com.adnanozgurcelik.foodland.util.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FoodEntity(
    @ColumnInfo("dairyFree") val dairyFree: Boolean?,
    @ColumnInfo("dishTypes") val dishTypes: List<String>?,
    @ColumnInfo("glutenFree") val glutenFree: Boolean?,
    @ColumnInfo("healthScore") val healthScore: Int?,
    @ColumnInfo("image") val image: String?,
    @ColumnInfo("instructions") val instructions: String?,
    @ColumnInfo("readyInMinutes") val readyInMinutes: Int?,
    @ColumnInfo("servings") val servings: Int?,
    @ColumnInfo("score") val score: Double?,
    @ColumnInfo("summary") val summary: String?,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("vegan") val vegan: Boolean?,
    @ColumnInfo("vegetarian") val vegetarian: Boolean?,
    @PrimaryKey(autoGenerate = true) val dbId: Int? = null
)
