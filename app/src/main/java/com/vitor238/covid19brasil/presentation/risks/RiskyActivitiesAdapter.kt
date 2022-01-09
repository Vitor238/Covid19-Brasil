package com.vitor238.covid19brasil.presentation.risks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitor238.covid19brasil.databinding.ItemRiskBinding
import com.vitor238.covid19brasil.domain.model.RiskyActivity
import com.vitor238.covid19brasil.utils.extension.fromHTML
import com.vitor238.covid19brasil.utils.extension.getColorCompat

class RiskyActivitiesAdapter :
    ListAdapter<RiskyActivity, RiskyActivitiesAdapter.ViewHolder>(RiskyActivitiesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(binding: ItemRiskBinding) : RecyclerView.ViewHolder(binding.root) {

        private val title: TextView = binding.textTitle
        private val riskyActivityList: TextView = binding.textRiskyActivitiesList
        private val card: CardView = binding.card

        fun bind(item: RiskyActivity) {
            title.text = title.context.getString(item.title)
            riskyActivityList.text = riskyActivityList.context.getString(item.riskyActivities).fromHTML()
            card.setCardBackgroundColor(card.context.getColorCompat(item.backgroundColor))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    ItemRiskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class RiskyActivitiesDiffUtils : DiffUtil.ItemCallback<RiskyActivity>() {

        override fun areItemsTheSame(oldItem: RiskyActivity, newItem: RiskyActivity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RiskyActivity, newItem: RiskyActivity): Boolean {
            return oldItem == newItem
        }
    }
}