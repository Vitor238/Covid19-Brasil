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

class UsefulLinksFragment : Fragment(),AdapterLinks.OnLinkClickListener {

    private lateinit var fragmentView: View
    private lateinit var listLinks:List<UsefulLink>

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
                    "https://img.youtube.com/vi/2h8vc-voPNQ/mqdefault.jpg",
                    "https://youtu.be/2h8vc-voPNQ"
                ),

                UsefulLink(
                    getString(R.string.link_title_2),
                    getString(R.string.atila),
                    "https://img.youtube.com/vi/KOXNBA9b86I/mqdefault.jpg",
                    "https://youtu.be/KOXNBA9b86I"
                ),

                UsefulLink(
                    getString(R.string.link_title_3),
                    getString(R.string.ministry_of_health),
                    "https://img.youtube.com/vi/FJxNsQ1-ZGM/mqdefault.jpg",
                    "https://youtu.be/FJxNsQ1-ZGM"
                ),

                UsefulLink(
                    getString(R.string.link_title_4),
                    getString(R.string.atila),
                    "https://img.youtube.com/vi/X_HC8aCrHdA/mqdefault.jpg",
                    "https://youtu.be/X_HC8aCrHdA"
                ),

                UsefulLink(
                    getString(R.string.link_title_5),
                    "",
                    "https://yt3.ggpht.com/a/AATXAJxaG8deGjN0sIUSy16BRmtsCcmrmvKYeJCuxw=s176-c-k-c0x00ffffff-no-rj-mo",
                    "https://coronavirus.saude.gov.br"
                )
            )

            fragmentView.recyclerViewLinks.layoutManager = LinearLayoutManager(fragmentActivity)
            fragmentView.recyclerViewLinks.adapter =
                AdapterLinks(fragmentActivity, listLinks,this)
        }
    }

    override fun onLinkClick(usefulLink: UsefulLink) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(usefulLink.link))
        startActivity(browserIntent)
    }
}