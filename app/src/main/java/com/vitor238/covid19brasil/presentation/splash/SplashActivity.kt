package com.vitor238.covid19brasil.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.vitor238.covid19brasil.databinding.ActivitySplashBinding
import com.vitor238.covid19brasil.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(
                this,
                MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
