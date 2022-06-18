package com.diego.poketinder.data.network

import com.diego.poketinder.data.model.PokemonListModel
import com.diego.poketinder.viewmodel.PokemonDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(private val pokemonApi: PokemonApi){
    suspend fun getPokemons(): PokemonListModel{
        return withContext(Dispatchers.IO){
            val res: Response<PokemonListModel> = pokemonApi.getPokemons()
            res.body()!!
        }
    }

    suspend fun getPokemonById(id: String):PokemonDetailModel{
        return withContext(Dispatchers.IO){
            val res: Response<PokemonDetailModel> = pokemonApi.getDetailPokemon(id)
            res.body()!!
        }
    }
}