package com.vitor238.covid19brasil.ui.cases

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitor238.covid19brasil.data.model.BrazilianState
import com.vitor238.covid19brasil.databinding.ItemBrazilianStateBinding
import com.vitor238.covid19brasil.utils.StatesUtils

class AdapterBrazilianStates :
    ListAdapter<BrazilianState, AdapterBrazilianStates.ViewHolder>(BrazilianStatesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(binding: ItemBrazilianStateBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageState: ImageView = binding.imageBrazilianState
        private val textStateName: TextView = binding.textBrazilianStateName
        private val textNumberConfirmed: TextView = binding.textNumberConfirmed
        private val textNumberDeaths: TextView = binding.textNumberDeaths

        fun bind(brazilianState: BrazilianState) {

            Glide.with(imageState.context).load(StatesUtils.getFlag(brazilianState.uf))
                .into(imageState)
            textStateName.text = brazilianState.state
            textNumberConfirmed.text = brazilianState.cases
            textNumberDeaths.text = brazilianState.deaths

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemBrazilianStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return ViewHolder(binding)
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

    companion object {
        private val TAG = AdapterBrazilianStates::class.simpleName
    }
}