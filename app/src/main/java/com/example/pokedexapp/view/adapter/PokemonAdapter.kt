package com.example.pokedexapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.api.model.PokemonItemResponse
import com.example.pokedexapp.view.PokemonViewHolder

class PokemonAdapter(var pokemonList: List<PokemonItemResponse> = emptyList(), private val onItemSelected: (String) -> Unit): RecyclerView.Adapter<PokemonViewHolder>() {

    fun updateList(list: List<PokemonItemResponse>){
      pokemonList = list
      notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
       return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
       holder.bind(pokemonList[position], onItemSelected)

    }

}