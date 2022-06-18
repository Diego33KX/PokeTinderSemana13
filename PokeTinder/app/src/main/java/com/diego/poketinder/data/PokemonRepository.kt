package com.diego.poketinder.data

import com.diego.poketinder.data.database.dao.PokemonDao
import com.diego.poketinder.data.database.entities.MyPokemonEntity
import com.diego.poketinder.data.model.PokemonListModel
import com.diego.poketinder.data.model.PokemonModel
import com.diego.poketinder.data.network.PokemonService
import com.diego.poketinder.domain.model.MyPokemon
import com.diego.poketinder.domain.model.Pokemon
import com.diego.poketinder.domain.model.PokemonDetail
import com.diego.poketinder.domain.model.toDomain
import com.diego.poketinder.viewmodel.PokemonDetailModel
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
){
    suspend fun getAllPokemonFromApi(): List<Pokemon>{
        val listResponse: PokemonListModel = pokemonService.getPokemons()
        val response: List<PokemonModel> = listResponse.result
        return response.map{it.toDomain()}
    }

    suspend fun getPokemonDetailFromApi(idPokemon: String): PokemonDetailModel{
        return pokemonService.getPokemonById(idPokemon)
    }

    suspend fun getMyPokemonsFromDatabase(): List<MyPokemon>{
        val response: List<MyPokemonEntity> = pokemonDao.getAllPokemons()
        return response.map{it.toDomain()}
    }

    suspend fun insertMyPokemon(myPokemon:MyPokemonEntity){
        pokemonDao.insert(myPokemon)
    }

    suspend fun deleteAllMyPokemon(){
        pokemonDao.deleteTable()
    }
}