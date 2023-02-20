package dev.echavez.cocktail.data.model

import com.google.gson.annotations.SerializedName

data class DrinksModel (
    @SerializedName("drinks")  val drinks: List<Drink>?
)