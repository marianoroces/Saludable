package com.marianoroces.norris.tpfinal.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModel
import com.marianoroces.norris.tpfinal.api.implementation.DrinksApi
import com.marianoroces.norris.tpfinal.model.Drinks
import retrofit2.Call

class DrinkViewModel() : ViewModel() {

    fun getDrink(): Call<Drinks> {
        val api = DrinksApi()

        return api.getDrink()
    }

    fun getIngredients(data: Drinks.Drink): ArrayList<String> {
        var ingredientes = ArrayList<String>()

        if (data.strIngredient1 != null)
            ingredientes.add(data.strIngredient1 + ": " + data.strMeasure1)

        if (data.strIngredient2 != null)
            ingredientes.add(data.strIngredient2 + ": " + data.strMeasure2)

        if (data.strIngredient3 != null)
            ingredientes.add(data.strIngredient3 + ": " + data.strMeasure3)

        if (data.strIngredient4 != null)
            ingredientes.add(data.strIngredient4 + ": " + data.strMeasure4)

        if (data.strIngredient5 != null)
            ingredientes.add(data.strIngredient5 + ": " + data.strMeasure5)

        if (data.strIngredient6 != null)
            ingredientes.add(data.strIngredient6 + ": " + data.strMeasure6)

        if (data.strIngredient7 != null)
            ingredientes.add(data.strIngredient7 + ": " + data.strMeasure7)

        if (data.strIngredient8 != null)
            ingredientes.add(data.strIngredient8 + ": " + data.strMeasure8)

        if (data.strIngredient9 != null)
            ingredientes.add(data.strIngredient9 + ": " + data.strMeasure9)

        if (data.strIngredient10 != null)
            ingredientes.add(data.strIngredient10 + ": " + data.strMeasure10)

        if (data.strIngredient11 != null)
            ingredientes.add(data.strIngredient11 + ": " + data.strMeasure11)

        if (data.strIngredient12 != null)
            ingredientes.add(data.strIngredient12 + ": " + data.strMeasure12)

        if (data.strIngredient13 != null)
            ingredientes.add(data.strIngredient13 + ": " + data.strMeasure13)

        if (data.strIngredient14 != null)
            ingredientes.add(data.strIngredient14 + ": " + data.strMeasure14)

        if (data.strIngredient15 != null)
            ingredientes.add(data.strIngredient15 + ": " + data.strMeasure15)

        return ingredientes
    }

    fun checkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}