package com.example.trabajoandroid.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

//La clase recibe un parámetro (El nombre del activity donde va a funcionar el SharedPreferences)
class SharedPref(activity: Activity) {

    private var pref: SharedPreferences? = null

    //Constructor
    init {
        pref = activity.getSharedPreferences("com.example.trabajoandroid", Context.MODE_PRIVATE)
    }

    //Guardamos un objeto en SharedPrefence (Any = Cualquier tipo)
    fun save(key: String, objeto: Any){

        try {

            //Gson: Convierte objetos Java en su representación Json y viceversa
            val gson = Gson()
            val json = gson.toJson(objeto)

            //Almacena en SharedPreference la sesión (Dispositivo)
            with(pref?.edit())
            {
                this?.putString(key, json) //clave-valor
                this?.commit()
            }
        }
        catch (e: Exception){

            Log.d("ERROR", "Error: ${e.message}")
        }
    }

    //Obtenemos el String almacenado en SharedPreference
    fun getData(key: String): String? {

        //Redundancia
        /*val data = pref?.getString(key, "")
        return data*/

        return pref?.getString(key, "")
    }

    //Función que elimina la info guardada en SharedPreferences
    fun remove(key: String){

        pref?.edit()?.remove(key)?.apply()
    }
}