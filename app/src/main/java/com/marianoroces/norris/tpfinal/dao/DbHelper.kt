package com.marianoroces.norris.tpfinal.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.marianoroces.norris.tpfinal.model.Meal
import com.marianoroces.norris.tpfinal.model.User

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION), IDbHelper {

    companion object {
        private val DATABASE_NAME = "saludable.db"
        private val DATABASE_VERSION = 1

        val TABLE_USERS = "usuarios"
        val COLUMN_ID_USERS = "id"
        val COLUMN_USUARIO_USERS = "usuario"
        val COLUMN_PASSWORD_USERS = "password"
        val COLUMN_DNI_USERS = "dni"
        val COLUMN_NOMBRE_USERS = "nombre"
        val COLUMN_APELLIDO_USERS = "apellido"
        val COLUMN_FECHA_NACIMIENTO_USERS = "fecha_nacimiento"
        val COLUMN_SEXO_USERS = "sexo"
        val COLUMN_PROVINCIA_USERS = "provincia"
        val COLUMN_LOCALIDAD_USERS = "localidad"
        val COLUMN_TRATAMIENTO_USERS = "tratamiento"

        val TABLE_MEAL = "comidas"
        val COLUMN_ID_MEAL = "id"
        val COLUMN_APELLIDO_MEAL = "apellido"
        val COLUMN_NOMBRE_MEAL = "nombre"
        val COLUMN_BEBIDA_MEAL = "bebida"
        val COLUMN_FECHA_MEAL = "fecha"
        val COLUMN_HAMBRE_MEAL = "hambre"
        val COLUMN_POSTRE_MEAL = "postre"
        val COLUMN_POSTRE_NOMBRE_MEAL = "nombrePostre"
        val COLUMN_PRINCIPAL_MEAL = "principal"
        val COLUMN_SECUNDARIA_MEAL = "secundaria"
        val COLUMN_TENTACION_MEAL = "tentacion"
        val COLUMN_TENTACION_NOMBRE_MEAL = "nombreTentacion"
        val COLUMN_TIPO_COMIDA_MEAL = "tipoComida"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsers =
            ("CREATE TABLE " + TABLE_USERS + " ( " + COLUMN_ID_MEAL + " INTEGER PRIMARY KEY, " +
                    COLUMN_USUARIO_USERS + " TEXT, " +
                    COLUMN_PASSWORD_USERS + " TEXT, " +
                    COLUMN_DNI_USERS + " TEXT, " +
                    COLUMN_NOMBRE_USERS + " TEXT, " +
                    COLUMN_APELLIDO_USERS + " TEXT, " +
                    COLUMN_FECHA_NACIMIENTO_USERS + " TEXT, " +
                    COLUMN_SEXO_USERS + " TEXT, " +
                    COLUMN_PROVINCIA_USERS + " TEXT, " +
                    COLUMN_LOCALIDAD_USERS + " TEXT, " +
                    COLUMN_TRATAMIENTO_USERS + " TEXT ) ")

        val createTableMeals =
            ("CREATE TABLE " + TABLE_MEAL + " ( " + COLUMN_ID_USERS + " INTEGER PRIMARY KEY, " +
                    COLUMN_APELLIDO_MEAL + " TEXT, " +
                    COLUMN_NOMBRE_MEAL + " TEXT, " +
                    COLUMN_BEBIDA_MEAL + " TEXT, " +
                    COLUMN_FECHA_MEAL + " TEXT, " +
                    COLUMN_HAMBRE_MEAL + " TEXT, " +
                    COLUMN_POSTRE_MEAL + " TEXT, " +
                    COLUMN_POSTRE_NOMBRE_MEAL + " TEXT, " +
                    COLUMN_PRINCIPAL_MEAL + " TEXT, " +
                    COLUMN_SECUNDARIA_MEAL + " TEXT, " +
                    COLUMN_TENTACION_MEAL + " TEXT, " +
                    COLUMN_TENTACION_NOMBRE_MEAL + " TEXT, " +
                    COLUMN_TIPO_COMIDA_MEAL + " TEXT ) ")

        db?.execSQL(createTableUsers)
        db?.execSQL(createTableMeals)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL)
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        onCreate(db)
    }

    override fun getUser(username: String, password: String): User {
        val db = this.readableDatabase
        val query =
            "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USUARIO_USERS + " = '" + username + "' AND " + COLUMN_PASSWORD_USERS + " = '" + password + "'"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val user = cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_USERS))
            val pass = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD_USERS))
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_USERS))
            val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_USERS))
            val fechaNacimiento = cursor.getString(
                cursor.getColumnIndex(
                    COLUMN_FECHA_NACIMIENTO_USERS
                )
            )
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO_USERS))
            val provincia = cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCIA_USERS))
            val localidad = cursor.getString(cursor.getColumnIndex(COLUMN_LOCALIDAD_USERS))
            val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI_USERS))
            val tratamiento = cursor.getString(cursor.getColumnIndex(COLUMN_TRATAMIENTO_USERS))

            return User(
                user,
                pass,
                dni,
                nombre,
                apellido,
                fechaNacimiento,
                sexo,
                provincia,
                localidad,
                tratamiento
            )
        } else {
            return User(
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy",
                "dummy"
            )
        }
    }

    fun saveUser(user: User): User {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_USUARIO_USERS, user.user)
            values.put(COLUMN_PASSWORD_USERS, user.password)
            values.put(COLUMN_DNI_USERS, user.dni)
            values.put(COLUMN_NOMBRE_USERS, user.nombre)
            values.put(COLUMN_APELLIDO_USERS, user.apellido)
            values.put(COLUMN_FECHA_NACIMIENTO_USERS, user.fechaNacimiento)
            values.put(COLUMN_SEXO_USERS, user.sexo)
            values.put(COLUMN_PROVINCIA_USERS, user.provincia)
            values.put(COLUMN_LOCALIDAD_USERS, user.localidad)
            values.put(COLUMN_TRATAMIENTO_USERS, user.tratamiento)

            db.insert(TABLE_USERS, null, values)

            return user
        } catch (e: Exception) {
            Log.d("DATABASE_ERROR", e.message.toString())
            return User("", "", "", "", "", "", "", "", "", "")
        }
    }

    override fun saveMeal(meal: Meal): Boolean {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_APELLIDO_MEAL, meal.apellido)
            values.put(COLUMN_NOMBRE_MEAL, meal.nombre)
            values.put(COLUMN_BEBIDA_MEAL, meal.bebida)
            values.put(COLUMN_FECHA_MEAL, meal.fecha)
            values.put(COLUMN_HAMBRE_MEAL, meal.hambre)
            values.put(COLUMN_POSTRE_MEAL, meal.postre)
            values.put(COLUMN_POSTRE_NOMBRE_MEAL, meal.nombrePostre)
            values.put(COLUMN_PRINCIPAL_MEAL, meal.principal)
            values.put(COLUMN_SECUNDARIA_MEAL, meal.secundaria)
            values.put(COLUMN_TENTACION_MEAL, meal.tentacion)
            values.put(COLUMN_TENTACION_NOMBRE_MEAL, meal.tentacionNombre)
            values.put(COLUMN_TIPO_COMIDA_MEAL, meal.tipoComida)

            db.insert(TABLE_MEAL, null, values)

            return true
        } catch (e: Exception) {
            Log.d("DATABASE_ERROR", e.message.toString())
            return false
        }
    }

    override fun getAllMeals(): ArrayList<Meal> {

        val db = this.readableDatabase
        val listaComidas: ArrayList<Meal> = ArrayList()
        val query = "SELECT * FROM " + TABLE_MEAL

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_MEAL))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_MEAL))
                val bebida = cursor.getString(cursor.getColumnIndex(COLUMN_BEBIDA_MEAL))
                val fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_MEAL))
                val hambre = cursor.getString(cursor.getColumnIndex(COLUMN_HAMBRE_MEAL))
                val postre = cursor.getString(cursor.getColumnIndex(COLUMN_POSTRE_MEAL))
                val nombrePostre =
                    cursor.getString(cursor.getColumnIndex(COLUMN_POSTRE_NOMBRE_MEAL))
                val principal = cursor.getString(cursor.getColumnIndex(COLUMN_PRINCIPAL_MEAL))
                val secundaria = cursor.getString(cursor.getColumnIndex(COLUMN_SECUNDARIA_MEAL))
                val tentacion = cursor.getString(cursor.getColumnIndex(COLUMN_TENTACION_MEAL))
                val nombreTentacion =
                    cursor.getString(cursor.getColumnIndex(COLUMN_TENTACION_NOMBRE_MEAL))
                val tipoComida = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_COMIDA_MEAL))

                listaComidas.add(
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
                        nombreTentacion,
                        hambre
                    )
                )
            } while (cursor.moveToNext())
        }
        return listaComidas
    }

    fun resetMeals() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_MEAL)
    }
}