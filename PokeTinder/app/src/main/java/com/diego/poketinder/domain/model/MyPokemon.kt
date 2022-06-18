package com.diego.poketinder.domain.model

import com.diego.poketinder.data.database.entities.MyPokemonEntity

data class MyPokemon(
    val id: Int = 0,
    val idPokemon: String,
    val name: String,
    val image: String,
    val isLegendary: Boolean = false,
    val position: Int = -1
)

fun MyPokemonEntity.toDomain() = MyPokemon(id=id,
    idPokemon = idPokemon,
    name = name,
    image = image,
    isLegendary = isLegendary,
    position = position
)