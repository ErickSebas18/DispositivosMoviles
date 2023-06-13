package com.programacion.dispositivosmoviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.programacion.dispositivosmoviles.databinding.ActivitySecondBinding
import com.programacion.dispositivosmoviles.fragments.FirstFragment
import com.programacion.dispositivosmoviles.fragments.SecondFragment
import com.programacion.dispositivosmoviles.fragments.ThirdFragment
import com.programacion.dispositivosmoviles.utilities.FragmentsManager

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d("UCE", "Loading...")

        var name = ""
        intent.extras.let {
            name = it?.getString("var1")!!
        }
        Log.d("UCE", "Hola ${name}")
        binding.txtWelcome.text = "Bienvenido " + name
        initClass()

        FragmentsManager().add(supportFragmentManager,binding.container.id,FirstFragment())
    }

    private fun initClass() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {


                    /* val newFragment = FirstFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.container.id, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit*/
                    true
                }

                R.id.favoritos -> {
                    val newFragment = SecondFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.container.id, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                R.id.app -> {
                    val newFragment = ThirdFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.container.id, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                else -> false
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}