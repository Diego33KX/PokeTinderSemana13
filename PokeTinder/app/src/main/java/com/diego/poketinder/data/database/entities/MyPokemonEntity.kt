package com.diego.poketinder.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diego.poketinder.domain.model.MyPokemon

@Entity(tableName = "pokemon_table")
data class MyPokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int = 0,
    @ColumnInfo(name = "idPokemon") val idPokemon: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "isLegendary") val isLegendary: Boolean = false,
    @ColumnInfo(name = "position") val position: Int = -1
)
fun MyPokemon.toDatabase() = MyPokemonEntity(id=id,
        idPokemon = idPokemon,
        name = name,
        image = image,
        isLegendary = isLegendary,
        position = position
)