package com.programacion.dispositivosmoviles.logic.validator.jikanLogic

import com.programacion.dispositivosmoviles.connections.ApiConnection
import com.programacion.dispositivosmoviles.data.entities.Superheroes
import com.programacion.dispositivosmoviles.endPoint.JikanEndPoint


class JikanAnimesLogic {


    suspend fun getAllAnimes(): List<Superheroes> {
        var call = ApiConnection.getJikanConnection()
        val response = call.create(JikanEndPoint::class.java).getAllAnimes()

        var itemList = arrayListOf<Superheroes>()
        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                val m = Superheroes(
                    it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url
                )
                itemList.add(m)
            }
        }
        return itemList
    }
}