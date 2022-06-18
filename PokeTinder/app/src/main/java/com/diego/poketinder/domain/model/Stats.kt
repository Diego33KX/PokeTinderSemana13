package com.diego.poketinder.domain.model
import com.diego.poketinder.data.model.StatsModel
import com.google.gson.annotations.SerializedName

data class Stats(
    val base_stat: Int,
    val stat: Stat
)
