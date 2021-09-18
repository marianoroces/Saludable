package com.marianoroces.norris.tpfinal.dao

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.marianoroces.norris.tpfinal.model.Meal

class FirebaseUtils {
    val firestoreDatabase = FirebaseFirestore.getInstance()

    fun saveMeal(meal: Meal): Boolean {
        var resultado = false

        val hashMap = hashMapOf<String, Any>(
            "apellido" to meal.apellido,
            "nombre" to meal.nombre,
            "bebida" to meal.bebida,
            "fecha" to meal.fecha,
            "hambre" to meal.hambre,
            "postre" to meal.postre,
            "nombrePostre" to meal.nombrePostre,
            "principal" to meal.principal,
            "secundaria" to meal.secundaria,
            "tentacion" to meal.tentacion,
            "tentacionNombre" to meal.tentacionNombre,
            "tipoComida" to meal.tipoComida
        )
        firestoreDatabase.collection("Persona")
            .add(hashMap)
            .addOnSuccessListener { document ->
                Log.d("API", document.id)
                resultado = true
            }
            .addOnFailureListener{ e ->
                Log.d("API", e.message.toString())
            }
        return resultado
    }
}