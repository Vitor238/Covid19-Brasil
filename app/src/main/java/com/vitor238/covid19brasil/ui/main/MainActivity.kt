package com.vitor238.covid19brasil.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.ActivityMainBinding
import com.vitor238.covid19brasil.ui.care.CareFragment
import com.vitor238.covid19brasil.ui.cases.CasesFragment
import com.vitor238.covid19brasil.ui.risks.RisksFragment
import com.vitor238.covid19brasil.ui.symptoms.SymptomsFragment
import com.vitor238.covid19brasil.ui.usefullinks.UsefulLinksFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMain.setOnNavigationItemSelectedListener(this)
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
