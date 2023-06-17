package com.programacion.dispositivosmoviles.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.adapters.MarvelAdapters
import com.programacion.dispositivosmoviles.data.entities.Superheroes
import com.programacion.dispositivosmoviles.databinding.FragmentSecondBinding
import com.programacion.dispositivosmoviles.logic.validator.Marvel

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val rvAdapter = MarvelAdapters(Marvel().returnMarvelChar())

        val rvMarvel = binding.rvMarvelChars

        rvMarvel.adapter = rvAdapter
        rvMarvel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
    }
}