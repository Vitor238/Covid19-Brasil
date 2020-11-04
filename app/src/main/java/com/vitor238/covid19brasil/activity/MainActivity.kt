package com.vitor238.covid19brasil.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        openFragment(CasesFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_cases -> {
                openFragment(CasesFragment())
            }
            R.id.navigation_symptoms -> {
                openFragment(SymptomsFragment())
            }
            R.id.navigation_care -> {
                openFragment(CareFragment())
            }
            R.id.navigation_risky_activities -> {
                openFragment(RisksFragment.newInstance())
            }
            R.id.navigation_useful_links -> {
                openFragment(UsefulLinksFragment())
            }
        }
        return true
    }

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
