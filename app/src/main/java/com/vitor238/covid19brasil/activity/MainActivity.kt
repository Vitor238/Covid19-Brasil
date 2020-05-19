package com.vitor238.covid19brasil.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.fragment.CasesFragment
import com.vitor238.covid19brasil.fragment.CareFragment
import com.vitor238.covid19brasil.fragment.UsefulLinksFragment
import com.vitor238.covid19brasil.fragment.SymptomsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val casesFragment = CasesFragment()
    private val symptomsFragment = SymptomsFragment()
    private val careFragment = CareFragment()
    private val usefulLinksFragment = UsefulLinksFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        openFragment(casesFragment)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_cases -> {
                openFragment(casesFragment)
            }
            R.id.navigation_symptoms -> {
                openFragment(symptomsFragment)
            }
            R.id.navigation_care -> {
                openFragment(careFragment)
            }
            R.id.navigation_useful_links -> {
                openFragment(usefulLinksFragment)
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
