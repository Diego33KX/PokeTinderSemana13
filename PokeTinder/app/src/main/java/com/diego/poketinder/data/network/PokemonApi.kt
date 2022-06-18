package com.diego.poketinder.data.network

import com.diego.poketinder.data.model.PokemonListModel
import com.diego.poketinder.viewmodel.PokemonDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("?limit=30")
    suspend fun getPokemons(): Response<PokemonListModel>

    @GET("{pokemon_id}")
    suspend fun getDetailPokemon(
        @Path("pokemon_id") pokemonId: String
    ): Response<PokemonDetailModel>
}