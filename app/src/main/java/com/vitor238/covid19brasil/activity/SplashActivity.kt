package com.vitor238.covid19brasil.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.vitor238.covid19brasil.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(
                this,
                MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
