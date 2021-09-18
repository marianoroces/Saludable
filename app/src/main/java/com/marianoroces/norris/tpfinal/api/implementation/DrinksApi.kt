package com.marianoroces.norris.tpfinal.api.implementation

import com.marianoroces.norris.tpfinal.api.IDrinksApi
import com.marianoroces.norris.tpfinal.model.Drinks
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DrinksApi {

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thecocktaildb.com/")
            .build()
    }

    fun getDrink(): Call<Drinks> {
        return getRetrofit().create(IDrinksApi::class.java).getDrink()
    }
}