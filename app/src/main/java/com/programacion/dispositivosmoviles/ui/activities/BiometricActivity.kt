package com.programacion.dispositivosmoviles.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.databinding.ActivityBiometricBinding
import com.programacion.dispositivosmoviles.ui.viewmodels.BiometricViewModel
import kotlinx.coroutines.launch

class BiometricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricBinding

    private val biometricViewModel by viewModels<BiometricViewModel> ()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.huella.setOnClickListener{
            autenticateBiometric()
        }

        biometricViewModel.isLoading.observe(this){isLoading ->
            if (isLoading){
                binding.main.visibility = View.GONE
                binding.mainCopia.visibility = View.VISIBLE
            }else {
                binding.main.visibility = View.VISIBLE
                binding.mainCopia.visibility = View.GONE
            }

        }

        lifecycleScope.launch {
            biometricViewModel.chargingData()
        }

    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun autenticateBiometric() {
        if (checkBiometric()){
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion requerida")
            .setSubtitle("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .setNegativeButtonText("Cancelar")
            .build()

        val biometricManager = BiometricPrompt(this, mainExecutor,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
            biometricManager.authenticate(biometricPrompt)
        } else {
            Snackbar.make(binding.huella, "No existe los requisitos necesarios",Snackbar.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun checkBiometric() : Boolean {
        var returnValid : Boolean = false
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(
            BIOMETRIC_STRONG
        )){
            BiometricManager.BIOMETRIC_SUCCESS -> {returnValid = true}
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {returnValid = false}
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {returnValid = false}
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val intentPrompt = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intentPrompt.putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG
                )
                startActivity(intentPrompt)
                returnValid = false
            }
        }
       return returnValid
    }
}