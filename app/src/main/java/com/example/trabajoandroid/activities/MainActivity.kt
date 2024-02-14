package com.example.trabajoandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.trabajoandroid.R
import com.example.trabajoandroid.models.Laptop
import com.example.trabajoandroid.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    //val mTag = "ListaLaptops"

    private var imageViewLaptop: ImageView? = null
    private var editTextCodigo: EditText? = null
    private var editTextDescripcion: EditText? = null
    private var spinnerMarca: Spinner? = null
    private var spinnerRam: Spinner? = null
    private var spinnerAlmacenamiento: Spinner? = null
    private var editTextPrecio: EditText? = null
    private var radioGroupColor: RadioGroup? = null
    private var radioButtonPlateado: RadioButton? = null
    private var radioButtonNegro: RadioButton? = null
    private var buttonRegistrar: Button? = null
    private var buttonGoToList: Button? = null

    private val gson = Gson()
    private var sharedPref: SharedPref? = null

    private var registroLaptops = ArrayList<Laptop>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        listeners()

    }

    private fun init(){

        sharedPref = SharedPref(this)

        imageViewLaptop = findViewById(R.id.imageview_laptop)
        editTextCodigo = findViewById(R.id.edittext_cod)
        editTextDescripcion = findViewById(R.id.edittext_descripcion)
        spinnerMarca = findViewById(R.id.spinner_marca)
        spinnerRam = findViewById(R.id.spinner_ram)
        spinnerAlmacenamiento = findViewById(R.id.spinner_almacenamiento)
        editTextPrecio = findViewById(R.id.edittext_precio)
        radioGroupColor = findViewById(R.id.radiogroup_color)
        radioButtonPlateado = findViewById(R.id.radiobutton_plateado)
        radioButtonNegro = findViewById(R.id.radiobutton_negro)
        buttonRegistrar = findViewById(R.id.btn_registrar)
        buttonGoToList = findViewById(R.id.btn_gotolist)

        val marcasLaptop = arrayOf("HP", "Lenovo", "Asus", "Samsung", "Toshiba")
        val marcasdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, marcasLaptop)
        marcasdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMarca?.adapter = marcasdapter

        val ramLaptop = arrayOf("8GB", "16GB", "32GB", "64GB")
        val ramAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ramLaptop)
        ramAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRam?.adapter = ramAdapter

        val almacenamientoLaptop = arrayOf("256GB", "512GB", "1024GB")
        val almacenamientoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, almacenamientoLaptop)
        almacenamientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAlmacenamiento?.adapter = almacenamientoAdapter

        Log.d(mTag, "JSON almacenado: ${sharedPref?.getData("laptop")}")
    }

    private fun listeners(){


        radioGroupColor?.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {

                R.id.radiobutton_plateado -> {
                    imageViewLaptop?.setImageResource(R.drawable.laptop_image_plateado)
                }
                R.id.radiobutton_negro -> {
                    imageViewLaptop?.setImageResource(R.drawable.laptop_image_negro)
                }
            }
        }

        buttonRegistrar?.setOnClickListener {
            register()
            resetForm()
        }

        buttonGoToList?.setOnClickListener { goToListaLaptops() }
    }

    private fun register(){

        val codigo = editTextCodigo?.text.toString()
        val descripcion = editTextDescripcion?.text.toString()
        val marca = spinnerMarca?.selectedItem.toString()
        val ram = spinnerRam?.selectedItem.toString()
        val almacenamiento = spinnerAlmacenamiento?.selectedItem.toString()
        val precio = editTextPrecio?.text.toString()
        val colorId = radioGroupColor?.checkedRadioButtonId
        val color = if (colorId == R.id.radiobutton_plateado) "Plateado" else "Negro"

        if (isValidForm(codigo = codigo, descripcion = descripcion, marca = marca, ram = ram, almacenamiento = almacenamiento, precio = precio, color = color))
        {
            getLaptopsFromSharedPref()

            val laptop = Laptop(
                codigo,
                descripcion,
                marca,
                ram,
                almacenamiento,
                precio.toDouble(),
                color
            )

            registroLaptops.add(laptop)

            saveLaptopInSharedPreferences(registroLaptops)
            Toast.makeText(this, "Los datos se registraron correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    //Almacena la data en SharedPreferences
    private fun saveLaptopInSharedPreferences(data: ArrayList<Laptop>) {

        sharedPref?.save("laptop", data)
    }

    private fun getLaptopsFromSharedPref(){

        if (!sharedPref?.getData("laptop").isNullOrBlank()){

            val type = object : TypeToken<ArrayList<Laptop>>() {}.type
            registroLaptops = gson.fromJson(sharedPref?.getData("laptop"), type)

        }
    }

    private fun isValidForm(codigo: String, descripcion: String, marca: String, ram: String, almacenamiento: String, precio: String, color: String): Boolean {

        if (codigo.isBlank()){

            Toast.makeText(this, "Debes ingresar el código", Toast.LENGTH_SHORT).show()
            return false
        }

        if (descripcion.isBlank()){

            Toast.makeText(this, "Debes ingresar la descripción", Toast.LENGTH_SHORT).show()
            return false
        }

        if (marca.isBlank()){

            Toast.makeText(this, "Debes ingresar la marca", Toast.LENGTH_SHORT).show()
            return false
        }

        if (ram.isBlank()){

            Toast.makeText(this, "Debes ingresar la RAM", Toast.LENGTH_SHORT).show()
            return false
        }

        if (almacenamiento.isBlank()){

            Toast.makeText(this, "Debes ingresar el almacenamiento", Toast.LENGTH_SHORT).show()
            return false
        }

        if (precio.isBlank()){

            Toast.makeText(this, "Debes ingresar el precio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (color.isBlank()) {

            Toast.makeText(this, "Debes confirmar el color", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    //Limpia los campos del formulario
    private fun resetForm(){

        editTextCodigo?.setText("")
        editTextDescripcion?.setText("")
        editTextPrecio?.setText("")
        radioButtonNegro?.isChecked = false
        radioButtonPlateado?.isChecked = false
        spinnerMarca?.setSelection(0)
        spinnerRam?.setSelection(0)
        spinnerAlmacenamiento?.setSelection(0)
    }

    private fun goToListaLaptops(){

        val i = Intent(this, ListaLaptopsRegistradas::class.java)
        startActivity(i)
    }
}