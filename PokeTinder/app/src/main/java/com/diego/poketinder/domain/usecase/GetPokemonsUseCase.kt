package com.diego.poketinder.domain.usecase

import com.diego.poketinder.data.PokemonRepository
import com.diego.poketinder.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke():List<Pokemon>{
        val myPokemonList = repository.getMyPokemonsFromDatabase()
        val allPokemon = repository.getAllPokemonFromApi()
        val myPokemonListIds = myPokemonList.map { it.idPokemon }
        return allPokemon.filter { it.getPokemonId() !in myPokemonListIds}
    }
}