package com.example.pokedexapp.api

import com.example.pokedexapp.api.model.PokemonDetailResponse
import com.example.pokedexapp.api.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{

    @GET("pokemon")
    suspend fun getListPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getDetailsPokemon(@Path("name") name: String): Response<PokemonDetailResponse>
}
