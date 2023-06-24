package com.programacion.dispositivosmoviles.connections

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConnection {

    /*Hacer el llamado: Conexión para la API*/
    fun getJikanConnection(): Retrofit{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}