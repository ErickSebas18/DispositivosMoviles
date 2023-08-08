package com.programacion.dispositivosmoviles.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.databinding.ActivityNotificacionBinding
import com.programacion.dispositivosmoviles.databinding.ActivityProgressBinding
import com.programacion.dispositivosmoviles.ui.viewmodels.ProgressViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding

    private val progressViewModel by viewModels<ProgressViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Livedata
        progressViewModel.progressState.observe(this, Observer {
            binding.progressBar.visibility = it
        })

        progressViewModel.items.observe(this, Observer {
            Toast.makeText(this, it[0].name, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NotificacionActivity::class.java))
        })

        //Listeners
        binding.btnProceso.setOnClickListener {
            progressViewModel.processBackground(3000)
        }

        binding.btnProceso2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                progressViewModel.getMarvelChars(0, 90)
            }
        }

    }
}