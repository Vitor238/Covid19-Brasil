package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.adapter.AdapterEstados
import com.vitor238.covid19brasil.api.BrasilService
import com.vitor238.covid19brasil.api.EstadosService
import com.vitor238.covid19brasil.extension.formatarNumero
import com.vitor238.covid19brasil.helper.RetrofitInitializer
import com.vitor238.covid19brasil.model.Brasil
import com.vitor238.covid19brasil.model.Estado
import com.vitor238.covid19brasil.model.ListaEstados
import com.vitor238.covid19brasil.model.Pais
import kotlinx.android.synthetic.main.fragment_brasil.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class BrasilFragment : Fragment() {

    private val retrofit = RetrofitInitializer().getRetrofit()
    private val listaEstados: MutableList<Estado> = mutableListOf()
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_brasil, container, false)
        return fragmentView
    }

    private fun configurarRecyclerViewEstados() {
        activity?.let { fragmentActivity ->
            fragmentView.recyclerViewEstados.layoutManager = LinearLayoutManager(fragmentActivity)
            fragmentView.recyclerViewEstados.setHasFixedSize(true)
            fragmentView.recyclerViewEstados.adapter =
                AdapterEstados(fragmentActivity, listaEstados)
        }
    }

    private fun carregarCasosPorEstado() {
        listaEstados.clear()
        val estadosService = retrofit.create(EstadosService::class.java)
        estadosService.casosPorEstado().enqueue(object : retrofit2.Callback<ListaEstados> {
            override fun onFailure(call: Call<ListaEstados>, t: Throwable) {
                Log.e("BrasilFragment", "Falha ao carregar dados dos Estados")
            }

            override fun onResponse(call: Call<ListaEstados>, response: Response<ListaEstados>) {
                if (response.isSuccessful) {
                    listaEstados.addAll(response.body()?.data ?: mutableListOf())
                    listaEstados.sortByDescending { it.deaths }
                    configurarRecyclerViewEstados()
                } else {
                    Log.e("BrasilFragment", "Erro: ${response.code()}")
                }
            }
        })
    }

    private fun carregarCasosBrasil() {
        val brasilService = retrofit.create(BrasilService::class.java)
        brasilService.casosBrasil().enqueue(object : retrofit2.Callback<Pais> {
            override fun onFailure(call: Call<Pais>, t: Throwable) {
                Log.e("BrasilFragment", "Falha ao carregar dados do Brasil")
            }

            override fun onResponse(call: Call<Pais>, response: Response<Pais>) {
                if (response.isSuccessful) {
                    val dadosBrasil = response.body()?.data
                    exibirDadosBrasil(dadosBrasil ?: Brasil())
                } else {
                    Log.e("BrasilFragment", "Erro: ${response.code()}")
                }
            }
        })
    }

    private fun exibirDadosBrasil(dadosBrasil: Brasil) {


        fragmentView.textNumeroConfirmados.text = dadosBrasil.confirmed.formatarNumero()
        fragmentView.textNumeroAtivos.text = dadosBrasil.cases.formatarNumero()
        fragmentView.textNumeroRecuperados.text = dadosBrasil.recovered.formatarNumero()
        fragmentView.textNumeroMortes.text = dadosBrasil.deaths.formatarNumero()
        fragmentView.textDataAualizacao.text = formatarData(dadosBrasil.updated_at)
    }

    private fun formatarData(dataAtualizacao: String): String {
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
        carregarCasosBrasil()
        carregarCasosPorEstado()
    }

    public fun String.formatarNumero(numero: Int): String {
        return NumberFormat.getIntegerInstance()
            .format(numero)
    }
}
