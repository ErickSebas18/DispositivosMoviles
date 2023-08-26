package com.programacion.dispositivosmoviles.ui.fragments

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.programacion.dispositivosmoviles.databinding.FragmentFirstBinding
import com.programacion.dispositivosmoviles.databinding.FragmentThirdBinding
import com.programacion.dispositivosmoviles.ui.activities.BiometricActivity
import com.programacion.dispositivosmoviles.ui.activities.CameraActivity
import com.programacion.dispositivosmoviles.ui.activities.NotificacionActivity
import com.programacion.dispositivosmoviles.ui.activities.ProgressActivity

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)

        binding.btnApp1.setOnClickListener{
            val intent = Intent(requireContext(), BiometricActivity::class.java)
            startActivity(intent)
        }

        binding.btnApp2.setOnClickListener{
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }
        binding.btnApp3.setOnClickListener{
            val intent = Intent(requireContext(), NotificacionActivity::class.java)
            startActivity(intent)
        }
        binding.btnApp4.setOnClickListener{
            val intent = Intent(requireContext(), ProgressActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}