package com.marianoroces.norris.tpfinal.model

import java.io.Serializable

data class User(
    val user: String,
    val password: String,
    val dni: String,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val sexo: String,
    val provincia: String,
    val localidad: String,
    val tratamiento: String
): Serializable