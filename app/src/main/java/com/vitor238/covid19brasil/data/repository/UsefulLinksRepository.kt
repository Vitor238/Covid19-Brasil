package com.vitor238.covid19brasil.data.repository

import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.data.domain.UsefulLink

class UsefulLinksRepository {

    fun getLinks() = listOf(
        UsefulLink(
            R.string.link_title_1,
            R.string.ministry_of_health,
            R.drawable.link_1,
            "https://youtu.be/2h8vc-voPNQ"
        ),

        UsefulLink(
            R.string.link_title_2,
            R.string.atila,
            R.drawable.link_2,
            "https://youtu.be/KOXNBA9b86I"
        ),

        UsefulLink(
            R.string.link_title_3,
            R.string.ministry_of_health,
            R.drawable.link_3,
            "https://youtu.be/FJxNsQ1-ZGM"
        ),

        UsefulLink(
            R.string.link_title_4,
            R.string.atila,
            R.drawable.link_4,
            "https://youtu.be/X_HC8aCrHdA"
        ),

        UsefulLink(
            R.string.link_title_5,
            null,
            R.drawable.link_5,
            "https://coronavirus.saude.gov.br"
        ),
        UsefulLink(
            R.string.link_title_6,
            null,
            R.drawable.link_6,
            "https://www.who.int/emergencies/diseases/novel-coronavirus-2019"
        ),
        UsefulLink(
            R.string.link_title_7,
            R.string.atila,
            R.drawable.link_7,
            "https://youtu.be/llLB1nwH1FE"
        ),
        UsefulLink(
            R.string.link_title_8,
            R.string.bbc,
            R.drawable.link_8,
            "https://www.bbc.com/portuguese/internacional-54014416"
        )
    )
}