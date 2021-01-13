package com.vitor238.covid19brasil.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.ActivitySplashBinding
import com.vitor238.covid19brasil.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(
                this,
                MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
