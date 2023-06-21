package com.programacion.dispositivosmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.programacion.dispositivosmoviles.data.entities.Superheroes
import com.programacion.dispositivosmoviles.databinding.ActivityDetailsMarvelItemBinding
import com.squareup.picasso.Picasso

class DetailsMarvelItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        /*intent.extras?.let {
            name = it.getString("name")
        }
        if(!name.isNullOrEmpty()){
            binding.txtName.text = name

        }*/

        var item = intent.getParcelableExtra<Superheroes>("name")
        if (item !== null){
            binding.txtName.text= item.nombre
            Picasso.get().load(item.imagen).into(binding.imgMarvel)
        }
    }
}