package com.diego.poketinder.domain.model
import com.diego.poketinder.data.model.AbilitiesModel
import com.diego.poketinder.data.model.StatsModel
import com.diego.poketinder.data.model.TypesModel
import com.diego.poketinder.viewmodel.PokemonDetailModel
import com.google.gson.annotations.SerializedName
data class PokemonDetail(
    val id: Int,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val stats: List<Stats>,
    val types: List<Types>,
    val abilities: List<Abilities>
)