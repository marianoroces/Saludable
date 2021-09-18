package com.marianoroces.norris.tpfinal.dao

import com.marianoroces.norris.tpfinal.model.Meal
import com.marianoroces.norris.tpfinal.model.User

interface IDbHelper {

    fun getUser(user: String, pass: String): User
    fun saveMeal(meal: Meal): Boolean
    fun getAllMeals(): ArrayList<Meal>
}