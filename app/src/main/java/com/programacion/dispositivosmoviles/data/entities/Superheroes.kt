package com.programacion.dispositivosmoviles.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Superheroes(val id: Int, val nombre: String, val comic: String, val imagen: String) : Parcelable