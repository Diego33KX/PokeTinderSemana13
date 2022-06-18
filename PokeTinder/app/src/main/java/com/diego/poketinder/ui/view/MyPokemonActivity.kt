package com.diego.poketinder.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.diego.poketinder.R
import com.diego.poketinder.databinding.ActivityMyPokemonBinding
import com.diego.poketinder.domain.model.MyPokemon
import com.diego.poketinder.ui.adapter.MyPokemonsAdapter

class MyPokemonActivity : BaseActivity<ActivityMyPokemonBinding>(ActivityMyPokemonBinding::inflate){
    private var listPokemonSaved = mutableListOf<MyPokemon>()
    private val adapter = MyPokemonsAdapter(listPokemonSaved)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pokemon)
        val pokemonId = intent?.extras?.getString("ID_POKEMON",null)
        if(pokemonId != null){
            Toast.makeText(this,"Pokemon id: $pokemonId",Toast.LENGTH_SHORT).show()
        }
    }
}