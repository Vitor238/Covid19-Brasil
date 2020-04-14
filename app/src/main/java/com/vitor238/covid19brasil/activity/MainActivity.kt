package com.vitor238.covid19brasil.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.fragment.BrasilFragment
import com.vitor238.covid19brasil.fragment.SintomasFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val brasilFragment = BrasilFragment()
    private val sintomasFragment = SintomasFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        abrirFragment(brasilFragment)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_brasil -> {
                abrirFragment(brasilFragment)
            }
            R.id.navigation_sintomas -> {
                abrirFragment(sintomasFragment)
            }
        }
        return true
    }

    private fun abrirFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
