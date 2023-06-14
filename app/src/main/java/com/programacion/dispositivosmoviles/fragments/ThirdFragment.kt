package com.programacion.dispositivosmoviles.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val opciones = arrayListOf<String>("Opcion1","Opcion2","Opcion3","Opcion4","Opcion5")

        val adapter = ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner, opciones)

        binding.spinner.adapter = adapter
        binding.listView.adapter = adapter
    }
}