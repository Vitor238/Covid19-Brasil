package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.AdapterSintomas
import com.vitor238.covid19brasil.model.Sintoma
import kotlinx.android.synthetic.main.fragment_sintomas.view.*

/**
 * A simple [Fragment] subclass.
 */

class SintomasFragment : Fragment() {

    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_sintomas, container, false)
        configurarRecyclerView()
        return fragmentView
    }

    private fun configurarRecyclerView() {
        activity?.let { fragmentActivity ->

            val listaSintomas = listOf(
                Sintoma(getString(R.string.fever), R.drawable.febre),
                Sintoma(getString(R.string.shortness_of_breath), R.drawable.falta_de_ar),
                Sintoma(getString(R.string.cough), R.drawable.tosse),
                Sintoma(getString(R.string.more_info), R.drawable.ministerio_da_saude)
            )

            fragmentView.recyclerViewSintomas.layoutManager = LinearLayoutManager(fragmentActivity)
            fragmentView.recyclerViewSintomas.setHasFixedSize(true)
            fragmentView.recyclerViewSintomas.adapter =
                AdapterSintomas(fragmentActivity, listaSintomas)
        }
    }

}
