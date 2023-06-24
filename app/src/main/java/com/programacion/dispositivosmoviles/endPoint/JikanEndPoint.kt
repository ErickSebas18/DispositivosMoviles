package com.programacion.dispositivosmoviles.endPoint

import retrofit2.Response
import com.programacion.dispositivosmoviles.data.entities.jikan.JikanAnimeEntity
import retrofit2.http.GET

interface JikanEndPoint {

    @GET("top/anime")
    suspend fun getAllAnimes(): Response<JikanAnimeEntity>
}