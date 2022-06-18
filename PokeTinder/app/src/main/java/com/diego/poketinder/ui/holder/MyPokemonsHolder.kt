package com.diego.poketinder.ui.holder

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.ankurg.expressview.ExpressView
import com.bumptech.glide.Glide

import com.diego.poketinder.databinding.ItemPokemonSavedBinding
import com.diego.poketinder.domain.model.MyPokemon
import com.diego.poketinder.util.formatNumberTo3Digits
import co.ankurg.expressview.OnCheckListener

class MyPokemonsHolder(view:View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPokemonSavedBinding.bind(view)
    fun bind(pokemon: MyPokemon) {
        with(binding) {
            tvIndex.text = formatNumberTo3Digits(pokemon.idPokemon.toInt())
            tvName.text = pokemon.name
            tvType.text = if (pokemon.isLegendary) "Legendary" else "Normal"
            Glide
                .with(itemView)
                .load(pokemon.image)
                .into(binding.ivPokemonLogo)
            likeButton.setOnCheckListener(object : OnCheckListener {
                override fun onChecked(view: ExpressView?) {
                    Log.d(TAG, "Checked")
                }
                override fun onUnChecked(view: ExpressView?) {
                    Log.d(TAG, "Unchecked")
                }
            })
    }
    }
}