package com.diego.poketinder.data.model
import com.google.gson.annotations.SerializedName
import com.diego.poketinder.viewmodel.PokemonDetailModel

data class StatsModel(
    @SerializedName("base_stat") val base_stat: Int,
    @SerializedName("stat") val stat: StatModel
)
