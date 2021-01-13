package com.vitor238.covid19brasil.ui.risks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitor238.covid19brasil.data.model.RiskyActivity
import com.vitor238.covid19brasil.databinding.ItemRiskBinding
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
            title.setTextColor(title.context.getColorCompat(item.textColor))
            riskyActivityList.text = riskyActivityList.context.getString(item.riskyActivities)
            riskyActivityList.setTextColor(riskyActivityList.context.getColorCompat(item.textColor))
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