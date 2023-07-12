package com.example.pokedexapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.api.model.PokemonItemResponse
import com.example.pokedexapp.databinding.ItemPokemonBinding

class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {



    private val binding = ItemPokemonBinding.bind(view)


    fun bind(pokemonItemResponse: PokemonItemResponse, onItemSelected: (String) -> Unit){
     binding.txtName.text = pokemonItemResponse.name
     binding.root.setOnClickListener { onItemSelected(pokemonItemResponse.name) }
    }

}