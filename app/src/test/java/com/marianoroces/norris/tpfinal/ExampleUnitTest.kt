package com.marianoroces.norris.tpfinal

import com.marianoroces.norris.tpfinal.dao.IDbHelper
import com.marianoroces.norris.tpfinal.model.Meal
import com.marianoroces.norris.tpfinal.model.User
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

class ExampleUnitTest {

    val db: IDbHelper = Mockito.mock(IDbHelper::class.java)
    val user: User = User("", "", "", "", "", "", "", "", "", "")
    val meal: Meal = Meal("", "", "", "", "", "", "", "", "", "", "", "")
    val list: ArrayList<Meal> = Mockito.mock(ArrayList::class.java) as ArrayList<Meal>

    @Before
    fun initializeElements() {
        Mockito.`when`(db.getUser("juan", "1234")).thenReturn(user)
        Mockito.`when`(db.saveMeal(meal)).thenReturn(true)
        Mockito.`when`(db.getAllMeals()).thenReturn(list)
    }

    @Test
    fun testGetUser() {
        assertEquals(db.getUser("juan", "1234"), user)
        assertNotEquals(db.getUser("pedro", "1234"), user)
        assertNotEquals(db.getUser("juan", "5678"), user)
    }

    @Test
    fun testSaveMeal(){
        assertTrue(db.saveMeal(meal))
    }

    @Test
    fun getAllMeals(){
        assertEquals(db.getAllMeals(), list)
        assertNotEquals(db.getAllMeals(), ArrayList<Meal>())
    }
}