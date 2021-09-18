package com.marianoroces.norris.tpfinal.viewmodel


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.ViewModel
import com.marianoroces.norris.tpfinal.dao.DbHelper
import com.marianoroces.norris.tpfinal.dao.FirebaseUtils
import com.marianoroces.norris.tpfinal.model.Meal

class MealViewModel() : ViewModel() {

    fun saveMeal(
        apellido: String,
        nombre: String,
        fecha: String,
        tipoComida: String,
        principal: String,
        secundaria: String,
        bebida: String,
        postre: String,
        nombrePostre: String,
        tentacion: String,
        tentacionNombre: String,
        hambre: String,
        context: Context
    ) {

        if (checkConnection(context)) {
            FirebaseUtils().saveMeal(
                Meal(
                    apellido,
                    nombre,
                    fecha,
                    tipoComida,
                    principal,
                    secundaria,
                    bebida,
                    postre,
                    nombrePostre,
                    tentacion,
                    tentacionNombre,
                    hambre
                )
            )
        } else {
            val db = DbHelper(context, null)

            if (db.saveMeal(
                    Meal(
                        apellido,
                        nombre,
                        fecha,
                        tipoComida,
                        principal,
                        secundaria,
                        bebida,
                        postre,
                        nombrePostre,
                        tentacion,
                        tentacionNombre,
                        hambre
                    )
                )
            )
                Log.d("DATABASE", "Guardado localmente")
        }
    }

    fun synchronize(context: Context) {
        val db = DbHelper(context, null)

        val listaComidas = db.getAllMeals()

        if (checkConnection(context)) {
            for (meal in listaComidas) {
                FirebaseUtils().saveMeal(meal)
            }
        }

        db.resetMeals()
    }

    private fun checkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}