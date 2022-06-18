package com.diego.poketinder.ui.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.diego.poketinder.R
import com.diego.poketinder.databinding.ActivityPokemonDetailBinding
import com.diego.poketinder.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : BaseActivity<ActivityPokemonDetailBinding>(ActivityPokemonDetailBinding::inflate){
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonDetailViewModel.isLoading.observe(this){
            binding.progressBar2.isVisible = it
        }
        pokemonDetailViewModel.pokemonDetailModel.observe(this){
            binding.tvName.text = it.name
            binding.tvPosition.text = it.id.toString()
            binding.tvExperienciaBase.text = it.base_experience.toString()
            binding.tvAltura.text = it.height.toString()
            binding.tvPeso.text = it.weight.toString()
            binding.tvHabilidades.text = it.abilities.elementAt(0).ability.name
            binding.tvEstadisticas.text = it.stats.elementAt(0).base_stat.toString()
            binding.tvTipo.text = it.types.elementAt(0).type.name
            val img=
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it.id}.png"
            Glide
                .with(this)
                .load(img)
                .into(binding.ivPokemonLogo)
        }

        val pokemonId = intent?.extras?.getString("ID_POKEMON",null)

        if(pokemonId != null){
            pokemonDetailViewModel.onCreate(pokemonId)
        }
        if(pokemonId != null){
            Toast.makeText(this,"Pokemon id: $pokemonId", Toast.LENGTH_SHORT).show()

        }
    }
}