package com.programacion.dispositivosmoviles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.data.entities.Superheroes
import com.programacion.dispositivosmoviles.databinding.MarvelCharactersBinding
import com.programacion.dispositivosmoviles.logic.validator.Marvel
import com.squareup.picasso.Picasso

class MarvelAdapters(
    private val dataSet: List<Superheroes>,
    private var fnClick: (Superheroes) -> Unit
) :
    RecyclerView.Adapter<MarvelAdapters.MarvelViewHolder>() {

    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)

        fun render(item: Superheroes, fnClick: (Superheroes) -> Unit) {
            binding.txtName.text = item.nombre
            binding.txtComic.text = item.comic
            Picasso.get().load(item.imagen).into(binding.imageView)


            binding.imageView.setOnClickListener {
                fnClick(item)
                //Snackbar.make(binding.imageView, item.nombre, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapters.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(inflater.inflate(R.layout.marvel_characters, parent, false))
    }

    override fun onBindViewHolder(holder: MarvelAdapters.MarvelViewHolder, position: Int) {
        holder.render(dataSet[position], fnClick)
    }

    override fun getItemCount(): Int = dataSet.size


}