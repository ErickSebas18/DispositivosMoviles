package com.programacion.dispositivosmoviles.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.programacion.dispositivosmoviles.DetailsMarvelItem
import com.programacion.dispositivosmoviles.MainActivity
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.adapters.MarvelAdapters
import com.programacion.dispositivosmoviles.data.entities.Superheroes
import com.programacion.dispositivosmoviles.databinding.FragmentSecondBinding
import com.programacion.dispositivosmoviles.logic.validator.Marvel
import com.programacion.dispositivosmoviles.logic.validator.jikanLogic.JikanAnimesLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRv()
            binding.rvSwipe.isRefreshing = false
        }
    }

    fun sendMarvelItems(item: Superheroes) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    fun chargeDataRv() {
        lifecycleScope.launch() {
            val rvAdapter =
                MarvelAdapters(JikanAnimesLogic().getAllAnimes()) { sendMarvelItems(it) }
            withContext(Dispatchers.Main) {
                with(binding.rvMarvelChars) {
                    this.adapter = rvAdapter
                    this.layoutManager =
                        LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }

    }
}
