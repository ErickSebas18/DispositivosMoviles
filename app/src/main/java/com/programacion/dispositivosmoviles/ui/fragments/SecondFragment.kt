package com.programacion.dispositivosmoviles.ui.fragments

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programacion.dispositivosmoviles.data.dataStore.UserDataStore
import com.programacion.dispositivosmoviles.data.marvel.MarvelChars
import com.programacion.dispositivosmoviles.data.marvel.getMarvelCharsDB
import com.programacion.dispositivosmoviles.databinding.FragmentFirstBinding
import com.programacion.dispositivosmoviles.databinding.FragmentSecondBinding
import com.programacion.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.programacion.dispositivosmoviles.ui.activities.DetailsMarvelItem
import com.programacion.dispositivosmoviles.ui.activities.dataStore
import com.programacion.dispositivosmoviles.ui.adapters.MarvelAdapter
import com.programacion.dispositivosmoviles.ui.utilities.DispositivosMoviles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private var rvAdapter: MarvelAdapter = MarvelAdapter(
        { item -> sendMarvelItem(item) }
    ) { item -> saveMarvelItem(item) }
    private lateinit var lmanager: LinearLayoutManager
    private var page = 1
    private lateinit var gManager: GridLayoutManager
    private var marvelCharsItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSecondBinding.inflate(
            layoutInflater, container, false
        )


        lmanager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )

        gManager = GridLayoutManager(requireActivity(), 2)
        return binding.root

    }

    override fun onStart() {
        super.onStart();

        lifecycleScope.launch(Dispatchers.IO) {
            getDataStore().collect() {user ->
                //binding.txtFilter.text = it
                Log.d("UCE", user.name)
                Log.d("UCES", user.email)
                Log.d("UCE", user.session)
            }
        }

        val names = arrayListOf<String>("A", "B", "C", "D", "E")

        /*val adapter1 = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_spinner_item,
            names
        )

        binding.spinner.adapter = adapter1*/
        chargeDataRV(5)

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV(5)
            binding.rvSwipe.isRefreshing = false
            lmanager.scrollToPositionWithOffset(5, 20)
        }

        binding.rvMarvelChars.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dx > 0) {
                        val v = lmanager.childCount//Cuantos han pasado
                        val p = lmanager.findFirstVisibleItemPosition()//Cual es mi posicion actual
                        val t = lmanager.itemCount//Cuantos tengo en total

                        if ((v + p) >= t) {
                            lifecycleScope.launch((Dispatchers.Main))
                            {
                                val x = with(Dispatchers.IO) {
                                    // MarvelLogic().getAllMarvelChars(0, 99)
                                    MarvelLogic().getMarvelChars(name = "spider", page * 3)
                                    //JikanAnimeLogic().getAllAnimes()
                                }
                                rvAdapter.updateListAdapter((x))

                            }
                        }

                    }

                }
            })

        binding.txtFilter.addTextChangedListener { filteredText ->
            val newItems = marvelCharsItems.filter { items ->
                items.name.lowercase().contains(filteredText.toString().lowercase())
            }

            rvAdapter.replaceListAdapter(newItems)
        }

    }

    fun corrotine() {
        lifecycleScope.launch(Dispatchers.Main) {
            var name = "Paul"

            name = withContext(Dispatchers.IO)
            {
                name = "Leonardo"
                return@withContext name
            }

            binding.cardView.radius
        }
    }

    fun sendMarvelItem(item: MarvelChars) {
        //Intent(contexto de la activity, .class de la activity)
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("item", item)//mandamos los items a la otra activity
        startActivity(i)
    }

    private fun getDataStore() =
        requireActivity().dataStore.data.map { prefs ->
            UserDataStore(
                name = prefs[stringPreferencesKey("usuario")].orEmpty(),
                email = prefs[stringPreferencesKey("email")].orEmpty(),
                session = prefs[stringPreferencesKey("session")].orEmpty()
            )
        }


    fun saveMarvelItem(item: MarvelChars): Boolean {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                DispositivosMoviles
                    .getDBInstance()
                    .marvelDao()
                    .insertMarvelCharacter(listOf(item.getMarvelCharsDB()))
            }
        }
        return item !== null
    }


    fun chargeDataRV(pos: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            //rvAdapter.items = JikanAnimeLogic().getAllAnimes()
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext MarvelLogic().getAllMarvelChars(0, 99)
            }


            rvAdapter.items = marvelCharsItems

            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter;
                this.layoutManager = gManager;
                //lmanager.scrollToPositionWithOffset(pos, 10)
            }
        }
        page++
    }

}