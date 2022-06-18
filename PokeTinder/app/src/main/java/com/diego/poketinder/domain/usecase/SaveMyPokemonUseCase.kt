package com.diego.poketinder.domain.usecase

import com.diego.poketinder.data.PokemonRepository
import com.diego.poketinder.data.database.entities.toDatabase
import com.diego.poketinder.domain.model.MyPokemon
import javax.inject.Inject

class SaveMyPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){

    suspend operator fun invoke(myPokemon: MyPokemon){
        pokemonRepository.insertMyPokemon(myPokemon.toDatabase())
    }
}