package com.diego.poketinder.domain.usecase

import com.diego.poketinder.data.PokemonRepository
import javax.inject.Inject

class DeleteAllMyPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository

) {
    suspend operator fun invoke(){
        pokemonRepository.deleteAllMyPokemon()
    }
}