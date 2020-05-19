package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.AdapterStates
import com.vitor238.covid19brasil.api.BrazilService
import com.vitor238.covid19brasil.api.StatesService
import com.vitor238.covid19brasil.extension.formatNumber
import com.vitor238.covid19brasil.helper.RetrofitInitializer
import com.vitor238.covid19brasil.model.Brazil
import com.vitor238.covid19brasil.model.Country
import com.vitor238.covid19brasil.model.ListStates
import com.vitor238.covid19brasil.model.State
import kotlinx.android.synthetic.main.fragment_cases.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CasesFragment : Fragment() {

    private val retrofit = RetrofitInitializer().getRetrofit()
    private val listStates: MutableList<State> = mutableListOf()
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_cases, container, false)
        return fragmentView
    }

    private fun setupRecyclerViewStates() {
        activity?.let { fragmentActivity ->
            fragmentView.recyclerViewEstados.layoutManager = LinearLayoutManager(fragmentActivity)
            fragmentView.recyclerViewEstados.setHasFixedSize(true)
            fragmentView.recyclerViewEstados.adapter =
                AdapterStates(fragmentActivity, listStates)
        }
    }

    private fun loadCasesByState() {
        listStates.clear()
        val statesService = retrofit.create(StatesService::class.java)
        statesService.casesByState().enqueue(object : retrofit2.Callback<ListStates> {
            override fun onFailure(call: Call<ListStates>, t: Throwable) {
                Log.e("BrazilFragment", "Failed to load state cases")
            }

            override fun onResponse(call: Call<ListStates>, response: Response<ListStates>) {
                if (response.isSuccessful) {
                    listStates.addAll(response.body()?.data ?: mutableListOf())
                    listStates.sortByDescending { it.deaths }
                    setupRecyclerViewStates()
                } else {
                    Log.e("BrazilFragment", "Error: ${response.code()}")
                }
            }
        })
    }

    private fun loadCasesBrazil() {
        val brazilService = retrofit.create(BrazilService::class.java)
        brazilService.casesBrazil().enqueue(object : retrofit2.Callback<Country> {
            override fun onFailure(call: Call<Country>, t: Throwable) {
                Log.e("BrazilFragment", "Failed to load brazil cases")
            }

            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                if (response.isSuccessful) {
                    val dadosBrasil = response.body()?.data
                    showDataBrazil(dadosBrasil ?: Brazil())
                } else {
                    Log.e("BrazilFragment", "Error: ${response.code()}")
                }
            }
        })
    }

    private fun showDataBrazil(dataBrazil: Brazil) {

        fragmentView.textNumberConfirmed.text = dataBrazil.confirmed.formatNumber()
        fragmentView.textNumeroAtivos.text = dataBrazil.cases.formatNumber()
        fragmentView.textNumeroRecuperados.text = dataBrazil.recovered.formatNumber()
        fragmentView.textNumberDeaths.text = dataBrazil.deaths.formatNumber()
        fragmentView.textDataAualizacao.text = formatDate(dataBrazil.updated_at)
    }

    private fun formatDate(dataAtualizacao: String): String {
        var simpleDateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val data = simpleDateFormat.parse(dataAtualizacao)
        return if (data != null) {
            simpleDateFormat = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getDefault()
            simpleDateFormat.format(data)
        } else {
            getString(R.string.error)
        }
    }

    override fun onStart() {
        super.onStart()
        loadCasesBrazil()
        loadCasesByState()
    }
}
