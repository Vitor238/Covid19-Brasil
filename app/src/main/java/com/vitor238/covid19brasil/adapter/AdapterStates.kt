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
import com.vitor238.covid19brasil.extension.formatNumber
import com.vitor238.covid19brasil.model.State

class AdapterStates(private val context: Context, private val listStates: List<State>) :
    RecyclerView.Adapter<AdapterStates.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_state, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listStates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = listStates[position]
        Glide.with(context).load("https://devarthurribeiro.github.io/covid19-brazil-api/" +
                "static/flags/${state.uf}.png")
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imageState)

        holder.textStateName.text = state.state
        holder.textNumberConfirmed.text = state.cases.formatNumber()
        holder.textNumberDeaths.text = state.deaths.formatNumber()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val imageState:ImageView = item.findViewById(R.id.imageState)
        val textStateName:TextView = item.findViewById(R.id.textStateName)
        val textNumberConfirmed:TextView = item.findViewById(R.id.textNumberConfirmed)
        val textNumberDeaths:TextView = item.findViewById(R.id.textNumberDeaths)
    }
}