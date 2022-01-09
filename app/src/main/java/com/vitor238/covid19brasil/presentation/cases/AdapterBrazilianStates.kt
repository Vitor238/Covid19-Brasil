package com.vitor238.covid19brasil.presentation.cases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitor238.covid19brasil.databinding.ItemBrazilianStateBinding
import com.vitor238.covid19brasil.domain.model.BrazilianState
import com.vitor238.covid19brasil.utils.extension.setStateFlag

class AdapterBrazilianStates(val onClickListener: (view: View, brazilianState: BrazilianState) -> Unit) :
    ListAdapter<BrazilianState, AdapterBrazilianStates.ViewHolder>(BrazilianStatesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBrazilianStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemBrazilianStateBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageState: ImageView = binding.imageBrazilianState
        private val textStateName: TextView = binding.textBrazilianStateName
        private val textNumberConfirmed: TextView = binding.textNumberConfirmed
        private val textNumberDeaths: TextView = binding.textNumberDeaths
        private val container: ConstraintLayout = binding.itemContainer

        fun bind(brazilianState: BrazilianState) {

            imageState.setStateFlag(brazilianState.uf)
            textStateName.text = brazilianState.state
            textNumberConfirmed.text = brazilianState.cases
            textNumberDeaths.text = brazilianState.deaths

            binding.root.setOnClickListener {
                onClickListener.invoke(container, brazilianState)
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
}