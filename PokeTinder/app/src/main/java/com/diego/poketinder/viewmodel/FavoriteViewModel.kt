package com.diego.poketinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.poketinder.domain.model.MyPokemon
import com.diego.poketinder.domain.usecase.DeleteAllMyPokemonsUseCase
import com.diego.poketinder.domain.usecase.GetMyPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getMyPokemonsUseCase: GetMyPokemonsUseCase,
    private val deleteAllMyPokemonsUseCase: DeleteAllMyPokemonsUseCase
) :ViewModel(){
    val myPokemonList = MutableLiveData<List<MyPokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMyPokemonsUseCase()

            myPokemonList.postValue(result)
            isLoading.postValue(false)
        }
    }
    fun deleteAllPokemon(){
        viewModelScope.launch {
            deleteAllMyPokemonsUseCase()
        }
    }
}