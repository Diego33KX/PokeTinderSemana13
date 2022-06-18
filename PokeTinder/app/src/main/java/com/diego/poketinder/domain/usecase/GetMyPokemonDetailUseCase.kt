package com.diego.poketinder.domain.usecase

import com.diego.poketinder.data.PokemonRepository
import com.diego.poketinder.viewmodel.PokemonDetailModel
import javax.inject.Inject

class GetMyPokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository){

    suspend operator fun invoke(id:String):PokemonDetailModel{
        return repository.getPokemonDetailFromApi(id)
    }
}
