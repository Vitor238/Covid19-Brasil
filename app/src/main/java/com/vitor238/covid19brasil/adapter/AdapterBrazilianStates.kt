package com.vitor238.covid19brasil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.extension.formatNumber
import com.vitor238.covid19brasil.helper.StatesUtils
import com.vitor238.covid19brasil.model.BrazilianState
import kotlinx.android.synthetic.main.item_brazilian_state.view.*

class AdapterBrazilianStates :
    ListAdapter<BrazilianState, AdapterBrazilianStates.ViewHolder>(BrazilianStatesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val imageState: ImageView = item.image_brazilian_state
        private val textStateName: TextView = item.text_brazilian_state_name
        private val textNumberConfirmed: TextView = item.text_number_confirmed
        private val textNumberDeaths: TextView = item.text_number_deaths

        fun bind(brazilianState: BrazilianState) {

            Glide.with(imageState.context).load(StatesUtils.getFlag(brazilianState.uf))
                .into(imageState)
            textStateName.text = brazilianState.state
            textNumberConfirmed.text = brazilianState.cases.formatNumber()
            textNumberDeaths.text = brazilianState.deaths.formatNumber()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_brazilian_state, parent,
                    false
                )
                return ViewHolder(view)
            }
        }
    }

    class BrazilianStatesDiffUtils : DiffUtil.ItemCallback<BrazilianState>() {
        override fun areItemsTheSame(oldItem: BrazilianState, newItem: BrazilianState): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: BrazilianState, newItem: BrazilianState): Boolean {
            return oldItem == newItem
        }
    }

    companion object{
        private val TAG = AdapterBrazilianStates::class.simpleName
    }
}