package com.capstone.floodforecast.view.splashscreen

import android.annotation.SuppressLint
import com.capstone.floodforecast.databinding.ActivitySplashScreenBinding
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.floodforecast.view.maps.MapsActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAction()
        supportActionBar?.hide()
    }

    private fun setUpAction() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MapsActivity::class.java))
            finish()
        }, 2000)
    }
}