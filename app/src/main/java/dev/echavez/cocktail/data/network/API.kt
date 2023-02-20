package dev.echavez.cocktail.data.network
import dev.echavez.cocktail.data.model.DrinksModel
import retrofit2.Response
import retrofit2.http.*

interface API{
    @GET
    suspend fun search(@Url url:String): Response<DrinksModel>
}
