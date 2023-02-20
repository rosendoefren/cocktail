package dev.echavez.cocktail.ui.First

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.echavez.cocktail.core.Retrofit
import dev.echavez.cocktail.data.model.Drink
import dev.echavez.cocktail.data.model.ExceptionCoroutine
import dev.echavez.cocktail.data.network.API
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FirstViewModel: ViewModel() {

    val drinks = MutableLiveData<List<Drink>?>()
    val exception = MutableLiveData<ExceptionCoroutine>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        exception.value = ExceptionCoroutine(404,"Erro: ${throwable.toString()}")
    }

    fun search(name: String){
        viewModelScope.launch(exceptionHandler) {
            val call = Retrofit.getTheCocktailDB()
                .create(API::class.java).search("search.php?s=${name}")
            val data = call.body()
            if(call.isSuccessful){
                if(!data?.drinks.isNullOrEmpty()){
                    drinks.value = data?.drinks
                }else{
                    exception.value = ExceptionCoroutine(501,"Is Empty")
                }
            }else{
                exception.value = ExceptionCoroutine(500,"Data error!")
            }
        }
    }
}