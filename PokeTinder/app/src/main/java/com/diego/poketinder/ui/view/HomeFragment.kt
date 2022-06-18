package com.diego.poketinder.ui.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.diego.poketinder.R
import com.diego.poketinder.databinding.HomeFragmentBinding
import com.diego.poketinder.domain.model.MyPokemon
import com.diego.poketinder.domain.model.Pokemon
import com.diego.poketinder.ui.adapter.PokemonAdapter
import com.diego.poketinder.viewmodel.HomeViewModel
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate),
CardStackListener, PokemonAdapter.Callback {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private var listPokemon:List<Pokemon> = emptyList()
    //INYECTA LAS DEPENDENCIAS DE HOME VIEW MODEL
    private val homeViewModel: HomeViewModel by viewModels()
    private val manager by lazy { CardStackLayoutManager(context,this) }
    private val adapter by lazy { PokemonAdapter(listPokemon,this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeTinderCard()
        observeValues()
        homeViewModel.onCreate()
    }
    private fun observeValues(){
        binding.floatingActionButton.setOnClickListener {
            // Rewind
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            binding.rvTinderPokemon.rewind()
        }

        binding.floatingActionButton2.setOnClickListener {
            // Skip
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        binding.floatingActionButton3.setOnClickListener {
            // Like
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        homeViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        homeViewModel.pokemonList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()

            binding.floatingActionButton.visibility = View.VISIBLE
            binding.floatingActionButton2.visibility = View.VISIBLE
            binding.floatingActionButton3.visibility = View.VISIBLE
        }
    }

    private fun initializeTinderCard() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager
        binding.rvTinderPokemon.adapter = adapter
        binding.rvTinderPokemon.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        //TODO("Not yet implemented")
    }

    override fun onCardSwiped(direction: Direction?) {
        if(direction == Direction.Right){
            val pokemon = adapter.list[manager.topPosition - 1]
            val myPokemon = MyPokemon(
                name = pokemon.name,
                image = pokemon.getPokemonImage(),
                idPokemon = pokemon.getPokemonId()
            )
            homeViewModel.savePokemonUseCase(myPokemon)
        }
    }

    override fun onCardRewound() {
        //TODO("Not yet implemented")
    }

    override fun onCardCanceled() {
        //TODO("Not yet implemented")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun onClickPokemonInformation(pokemon: Pokemon) {
        val intent = Intent(context, PokemonDetailActivity::class.java)
        intent.putExtra("ID_POKEMON", pokemon.getPokemonId())
        startActivity(intent)
    }

}