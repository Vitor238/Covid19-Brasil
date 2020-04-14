package com.vitor238.covid19brasil.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.model.Sintoma


class AdapterSintomas(val context: Context, val listaSintomas: List<Sintoma>) :
    RecyclerView.Adapter<AdapterSintomas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_sintomas, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaSintomas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemSintoma = listaSintomas[position]
        holder.textSintomas.text = itemSintoma.sintoma

        Glide.with(context).load(itemSintoma.imagem).apply(RequestOptions.circleCropTransform())
            .into(holder.imageSintomas)

        if (itemSintoma.sintoma == context.getString(R.string.more_info)) {

            holder.textSintomas.setOnClickListener {
                val url = "https://coronavirus.saude.gov.br/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
            }

        }
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imageSintomas: ImageView = item.findViewById(R.id.imageSintomas)
        val textSintomas: TextView = item.findViewById(R.id.textSintoma)
    }
}