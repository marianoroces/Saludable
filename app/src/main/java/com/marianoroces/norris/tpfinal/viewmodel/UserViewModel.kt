package com.marianoroces.norris.tpfinal.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marianoroces.norris.tpfinal.dao.DbHelper
import com.marianoroces.norris.tpfinal.model.User

class UserViewModel() : ViewModel() {

    val user = MutableLiveData<User>()

    fun saveUser(
        user: String,
        password: String,
        dni: String,
        nombre: String,
        apellido: String,
        fechaNacimiento: String,
        sexo: String,
        provincia: String,
        localidad: String,
        tratamiento: String,
        context: Context
    ): Boolean {
        val db = DbHelper(context, null)

        val responseUser = db.saveUser(
            User(
                user,
                password,
                dni,
                nombre,
                apellido,
                fechaNacimiento,
                sexo,
                provincia,
                localidad,
                tratamiento
            )
        )

        return if (responseUser.user != "dummy") {
            user(responseUser)
            true
        } else
            false

    }

    fun getUser(context: Context, username: String, password: String): Boolean {
        val db = DbHelper(context, null)

        val responseUser: User = db.getUser(username, password)

        return if (responseUser.user != "dummy") {
            user(responseUser)
            true
        } else
            false
    }

    fun user(info: User) {
        user.value = info
    }
}