package com.example.trabajoandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajoandroid.R
import com.example.trabajoandroid.adapter.LaptopsAdapter
import com.example.trabajoandroid.models.Laptop
import com.example.trabajoandroid.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ListaLaptopsRegistradas : AppCompatActivity(){

    private var recyclerViewLaptops: RecyclerView? = null
    private var toolbar: Toolbar? = null

    private var adapter: LaptopsAdapter? = null
    private var sharedPref: SharedPref? = null
    private val gson = Gson()
    private var listLaptops = ArrayList<Laptop>()

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_laptops_registradas)

        init()
        listeners()

    }

    private fun init(){

        sharedPref = SharedPref(this)

        recyclerViewLaptops = findViewById(R.id.recyclerview_laptops)
        searchView = findViewById(R.id.searchview_laptop)

        //Configuración toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar?.title = "Laptops registradas"

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerViewLaptops?.layoutManager = LinearLayoutManager(this)

        getLaptopsFromSharedPref()
    }

    private fun listeners(){

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                filterList(newText)
                return true
            }

        })
    }

    private fun getLaptopsFromSharedPref(){

        if (!sharedPref?.getData("laptop").isNullOrBlank()){

            val type = object : TypeToken<ArrayList<Laptop>>() {}.type
            listLaptops = gson.fromJson(sharedPref?.getData("laptop"), type)

            //Asignamos el adaptador al recycler
            adapter = LaptopsAdapter(this, listLaptops)
            recyclerViewLaptops?.adapter = adapter
            recyclerViewLaptops?.layoutManager?.scrollToPosition(listLaptops.size -1)
        }
    }

    private fun filterList(query: String?){

        if (query != null){

            val filteredList = ArrayList<Laptop>()

            for (i in listLaptops){

                if (i.codigo.toLowerCase(Locale.ROOT).contains(query)){

                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()){

                Toast.makeText(this, "Laptop no encontrada", Toast.LENGTH_SHORT).show()
                //Toast.makeText(this, "Contenido de filteredList: $filteredList", Toast.LENGTH_SHORT).show()
            }
            else{
                adapter?.setFilteredList(filteredList)
            }
        }
    }
}