package com.example.pokedexapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexapp.api.ApiService
import com.example.pokedexapp.databinding.ActivityMainBinding
import com.example.pokedexapp.view.PokemonDetailActivity.Companion.EXTRA_ID
import com.example.pokedexapp.view.adapter.PokemonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }



    private fun initUI() {
      getPokemonList(251,0)


      adapter = PokemonAdapter {navigateToDetail(it)}
      binding.rvPokemon.setHasFixedSize(true)
      binding.rvPokemon.layoutManager = LinearLayoutManager(this)
      binding.rvPokemon.adapter = adapter

    }

   private fun getPokemonList(limit:Int, offset:Int) {
       CoroutineScope(Dispatchers.IO).launch {
         val myResponse = retrofit.create(ApiService::class.java).getListPokemon(limit, offset)
         if (myResponse.isSuccessful){
            val response = myResponse.body()
             if (response != null){
                 Log.i("Luis", response.toString())
                  runOnUiThread {
                   adapter.updateList(response.results)
                  }
             }
         } else {
             Log.i("Luis", "No funciona el codigo")
         }
       }
   }



    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
       val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }


}