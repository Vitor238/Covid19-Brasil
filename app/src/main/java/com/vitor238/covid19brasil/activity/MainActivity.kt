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

        bottom_nav_main.setOnNavigationItemSelectedListener(this)
        openFragment(CasesFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cases -> {
                openFragment(CasesFragment.newInstance())
            }
            R.id.action_symptoms -> {
                openFragment(SymptomsFragment.newInstance())
            }
            R.id.action_care -> {
                openFragment(CareFragment.newInstance())
            }
            R.id.action_risky_activities -> {
                openFragment(RisksFragment.newInstance())
            }
            R.id.action_useful_links -> {
                openFragment(UsefulLinksFragment.newInstance())
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
