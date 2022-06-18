package com.diego.poketinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.poketinder.domain.usecase.GetMyPokemonDetailUseCase
import com.diego.poketinder.domain.usecase.GetMyPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getMyPokemonsDetailUseCase: GetMyPokemonDetailUseCase
): ViewModel(){
    val pokemonDetailModel = MutableLiveData<PokemonDetailModel>()
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate(idPokemon:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMyPokemonsDetailUseCase.invoke(idPokemon)
            pokemonDetailModel.postValue(result)
            isLoading.postValue(false)
        }
    }
}