package com.marianoroces.norris.tpfinal.api

import com.marianoroces.norris.tpfinal.model.Drinks
import retrofit2.Call
import retrofit2.http.GET

interface IDrinksApi {

    @GET("api/json/v1/1/random.php")
    fun getDrink(): Call<Drinks>
}