package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.AdapterBrazilianStates
import com.vitor238.covid19brasil.api.BrazilService
import com.vitor238.covid19brasil.api.BrazilianStatesService
import com.vitor238.covid19brasil.extension.formatNumber
import com.vitor238.covid19brasil.helper.RetrofitInitializer
import com.vitor238.covid19brasil.model.Brazil
import com.vitor238.covid19brasil.model.Country
import com.vitor238.covid19brasil.model.ListBrazilianStates
import kotlinx.android.synthetic.main.fragment_cases.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CasesFragment : Fragment() {

    private val retrofit = RetrofitInitializer().getRetrofit()
    private lateinit var fragmentView: View
    private lateinit var adapterBrazilianStates: AdapterBrazilianStates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterBrazilianStates = AdapterBrazilianStates()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_cases, container, false)

        fragmentView.recycler_brazilian_states.setHasFixedSize(true)

        loadCasesBrazil()
        loadCasesByState()

        return fragmentView
    }

    private fun loadCasesByState() {
        val statesService = retrofit.create(BrazilianStatesService::class.java)
        statesService.casesByState().enqueue(object : retrofit2.Callback<ListBrazilianStates> {

            override fun onResponse(call: Call<ListBrazilianStates>, response: Response<ListBrazilianStates>) {
                if (response.isSuccessful) {
                    val list = response.body()?.data
                    list?.sortedByDescending { it.cases }
                    adapterBrazilianStates.submitList(list)
                    fragmentView.recycler_brazilian_states.adapter = adapterBrazilianStates
                } else {
                    Log.e(TAG, "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ListBrazilianStates>, t: Throwable) {
                Log.e(TAG, "Failed to load state cases")
            }
        })
    }

    private fun loadCasesBrazil() {
        val brazilService = retrofit.create(BrazilService::class.java)
        brazilService.casesBrazil().enqueue(object : retrofit2.Callback<Country> {
            override fun onFailure(call: Call<Country>, t: Throwable) {
                Log.e(TAG, "Failed to load brazil cases")
            }

            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                if (response.isSuccessful) {
                    val brazilData = response.body()?.data
                    showDataBrazil(brazilData ?: Brazil())
                } else {
                    Log.e(TAG, "Error: ${response.code()}")
                }
            }
        })
    }

    private fun showDataBrazil(brazilData: Brazil) {

        fragmentView.text_number_confirmed.text = brazilData.confirmed.formatNumber()
        fragmentView.text_number_active.text = brazilData.cases.formatNumber()
        fragmentView.text_recovered_number.text = brazilData.recovered.formatNumber()
        fragmentView.text_number_deaths.text = brazilData.deaths.formatNumber()
        fragmentView.text_update_date.text = formatDate(brazilData.updated_at)
    }

    private fun formatDate(updateTime: String): String {
        var simpleDateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val data = simpleDateFormat.parse(updateTime)
        return if (data != null) {
            simpleDateFormat = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getDefault()
            simpleDateFormat.format(data)
        } else {
            getString(R.string.error)
        }
    }

    companion object {
        fun newInstance() = CasesFragment()
        private val TAG = CasesFragment::class.simpleName
    }
}
