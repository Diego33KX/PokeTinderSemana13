package com.diego.poketinder.domain.usecase

import com.diego.poketinder.data.PokemonRepository
import com.diego.poketinder.domain.model.MyPokemon
import javax.inject.Inject

class GetMyPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    suspend operator fun invoke(): List<MyPokemon>{
        return pokemonRepository.getMyPokemonsFromDatabase()
    }
}