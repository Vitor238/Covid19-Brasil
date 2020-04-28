package com.vitor238.covid19brasil.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.extension.formatarNumero
import com.vitor238.covid19brasil.model.Estado
import com.vitor238.covid19brasil.model.Sintoma

class AdapterEstados(private val context: Context, private val listaEstados: List<Estado>) :
    RecyclerView.Adapter<AdapterEstados.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_estados, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaEstados.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estado = listaEstados[position]
        Glide.with(context).load("https://devarthurribeiro.github.io/covid19-brazil-api/" +
                "static/flags/${estado.uf}.png")
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imagemEstado)

        holder.textNomeEstado.text = estado.state
        holder.textNumeroConfirmados.text = estado.cases.formatarNumero()
        holder.textNumeroMortes.text = estado.deaths.formatarNumero()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val imagemEstado:ImageView = item.findViewById(R.id.imagemEstado)
        val textNomeEstado:TextView = item.findViewById(R.id.textNomeEstado)
        val textNumeroConfirmados:TextView = item.findViewById(R.id.textNumeroConfirmados)
        val textNumeroMortes:TextView = item.findViewById(R.id.textNumeroMortes)
    }
}