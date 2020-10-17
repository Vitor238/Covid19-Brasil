package com.vitor238.covid19brasil.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.AdapterLinks
import com.vitor238.covid19brasil.model.UsefulLink
import kotlinx.android.synthetic.main.fragment_useful_links.view.*


/**
 * A simple [Fragment] subclass.
 */

class UsefulLinksFragment : Fragment(), AdapterLinks.OnLinkClickListener {

    private lateinit var fragmentView: View
    private lateinit var listLinks: List<UsefulLink>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_useful_links, container, false)
        setupRecyclerView()
        return fragmentView
    }

    private fun setupRecyclerView() {
        activity?.let { fragmentActivity ->
            listLinks = listOf(
                UsefulLink(
                    getString(R.string.link_title_1),
                    getString(R.string.ministry_of_health),
                    R.drawable.link_1,
                    "https://youtu.be/2h8vc-voPNQ"
                ),

                UsefulLink(
                    getString(R.string.link_title_2),
                    getString(R.string.atila),
                    R.drawable.link_2,
                    "https://youtu.be/KOXNBA9b86I"
                ),

                UsefulLink(
                    getString(R.string.link_title_3),
                    getString(R.string.ministry_of_health),
                    R.drawable.link_3,
                    "https://youtu.be/FJxNsQ1-ZGM"
                ),

                UsefulLink(
                    getString(R.string.link_title_4),
                    getString(R.string.atila),
                    R.drawable.link_4,
                    "https://youtu.be/X_HC8aCrHdA"
                ),

                UsefulLink(
                    getString(R.string.link_title_5),
                    "",
                    R.drawable.link_5,
                    "https://coronavirus.saude.gov.br"
                ),
                UsefulLink(
                    getString(R.string.link_title_6),
                    "",
                    R.drawable.link_6,
                    "https://www.who.int/emergencies/diseases/novel-coronavirus-2019"
                ),
                UsefulLink(
                    getString(R.string.link_title_7),
                    getString(R.string.atila),
                    R.drawable.link_7,
                    "https://youtu.be/llLB1nwH1FE"
                ),
                UsefulLink(
                    getString(R.string.link_title_8),
                    getString(R.string.bbc),
                    R.drawable.link_8,
                    "https://www.bbc.com/portuguese/internacional-54014416"
                )
            )

            fragmentView.recyclerViewLinks.layoutManager = LinearLayoutManager(fragmentActivity)
            fragmentView.recyclerViewLinks.adapter =
                AdapterLinks(fragmentActivity, listLinks, this)
        }
    }

    override fun onLinkClick(usefulLink: UsefulLink) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(usefulLink.link))
        startActivity(browserIntent)
    }
}