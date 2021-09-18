package com.marianoroces.norris.tpfinal.model

import java.io.Serializable

data class Meal(
    val apellido: String,
    val nombre: String,
    val fecha: String,
    val tipoComida: String,
    val principal: String,
    val secundaria: String,
    val bebida: String,
    val postre: String,
    val nombrePostre: String,
    val tentacion: String,
    val tentacionNombre: String,
    val hambre: String
): Serializable
