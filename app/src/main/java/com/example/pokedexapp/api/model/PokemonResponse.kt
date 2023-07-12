package com.example.pokedexapp.api.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val results: List<PokemonItemResponse>
)


data class PokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
