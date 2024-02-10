package com.example.trabajoandroid.models

import com.google.gson.annotations.SerializedName


class Laptop(

    @SerializedName("codigo") var codigo: String,
    @SerializedName("descripcion") var descripcion: String,
    @SerializedName("marca") var marca: String,
    @SerializedName("ram") var ram: String,
    @SerializedName("almacenamiento") var almacenamiento: String,
    @SerializedName("precio") var precio: Double,
    @SerializedName("color") var color: String?,
)