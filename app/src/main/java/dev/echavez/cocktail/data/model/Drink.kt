package dev.echavez.cocktail.data.model

import com.google.gson.annotations.SerializedName

data class Drink(
    val idDrink: String?,
    val strDrink: String?,
    val strDrinkAlternate: String?,
    val strTags: String?,
    val strCategory: String?,
    val strIBA: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strDrinkThumb: String?
)
