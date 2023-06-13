package com.programacion.dispositivosmoviles.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentsManager {

    fun replaceFragment(manager : FragmentManager,
                        container: Int,
                        fragment: Fragment){
        with(manager.beginTransaction()){
            replace(container,fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun add(manager : FragmentManager,
                        container: Int,
                        fragment: Fragment){
        with(manager.beginTransaction()){
            add(container,fragment)
            addToBackStack(null)
            commit()
        }
    }
}