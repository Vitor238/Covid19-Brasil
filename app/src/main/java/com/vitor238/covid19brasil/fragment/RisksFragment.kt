package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.RiskyActivitiesAdapter
import com.vitor238.covid19brasil.extension.getColorCompat
import com.vitor238.covid19brasil.model.RiskyActivity
import kotlinx.android.synthetic.main.fragment_risks.view.*

class RisksFragment : Fragment() {

    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_risks, container, false)
        setupRecyclerView()
        return fragmentView
    }

    private fun setupRecyclerView() {
        val riskyActivitiesList = listOf(
            RiskyActivity(
                getString(R.string.risk_1),
                getColorCompat(R.color.risk_1),
                getString(R.string.risky_activity_list_1),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_2),
                getColorCompat(R.color.risk_2),
                getString(R.string.risky_activity_list_2),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_3),
                getColorCompat(R.color.risk_3),
                getString(R.string.risky_activity_list_3),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_4),
                getColorCompat(R.color.risk_4),
                getString(R.string.risky_activity_list_4),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_5),
                getColorCompat(R.color.risk_5),
                getString(R.string.risky_activity_list_5),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_6),
                getColorCompat(R.color.risk_6),
                getString(R.string.risky_activity_list_6),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_7),
                getColorCompat(R.color.risk_7),
                getString(R.string.risky_activity_list_7),
                getColorCompat(R.color.black)
            ),
            RiskyActivity(
                getString(R.string.risk_8),
                getColorCompat(R.color.risk_8),
                getString(R.string.risky_activity_list_8),
                getColorCompat(R.color.white)
            ),
            RiskyActivity(
                getString(R.string.risk_9),
                getColorCompat(R.color.risk_9),
                getString(R.string.risky_activity_list_9),
                getColorCompat(R.color.white)
            )
        )

        val adapter = RiskyActivitiesAdapter()
        fragmentView.risky_activities_recyclerview.adapter = adapter
        adapter.submitList(riskyActivitiesList)

    }

    companion object {
        fun newInstance() = RisksFragment()
    }

}