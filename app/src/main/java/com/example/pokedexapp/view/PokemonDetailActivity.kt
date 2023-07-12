package com.example.pokedexapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedexapp.api.ApiService
import com.example.pokedexapp.api.model.PokemonDetailResponse
import com.example.pokedexapp.api.model.Stats
import com.example.pokedexapp.databinding.ActivityPokemonDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getPokemonInformation(id)
    }

    private fun getPokemonInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val pokemonDetail = getRetrofit().create(ApiService::class.java).getDetailsPokemon(id)

            if (pokemonDetail.body() != null) {
                runOnUiThread { createUI(pokemonDetail.body()!!) }
            }

        }
    }


    private fun createUI(pokemon: PokemonDetailResponse) {
        Picasso.get().load(pokemon.sprites.other.home.img).into(binding.ivPokemonDetail)
        binding.tvName.text = pokemon.name
        binding.weightTextnumber.text = pokemon.weight.toDouble().toString() + "KG"
        binding.heightTextnumber.text = pokemon.height.toDouble().toString() + "M"
        binding.determinateBar1.progress = pokemon.pokemonDetails[0].statValue
        binding.determinateBar2.progress = pokemon.pokemonDetails[1].statValue
        binding.determinateBar3.progress = pokemon.pokemonDetails[2].statValue
        binding.determinateBar4.progress = pokemon.pokemonDetails[3].statValue
        binding.determinateBar5.progress = pokemon.pokemonDetails[4].statValue
        binding.determinateBar6.progress = pokemon.pokemonDetails[5].statValue
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}