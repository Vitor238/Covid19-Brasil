package com.vitor238.covid19brasil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.model.RiskyActivity
import com.vitor238.covid19brasil.model.UsefulLink
import kotlinx.android.synthetic.main.item_risk.view.*

class RiskyActivitiesAdapter :
    ListAdapter<RiskyActivity,RiskyActivitiesAdapter.ViewHolder>(RiskyActivitiesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.text_title
        private val riskyActivityList: TextView = itemView.text_risky_activities_list
        private val card: CardView = itemView.card

        fun bind(item: RiskyActivity) {
            title.text = item.title
            title.setTextColor(item.textColor)
            riskyActivityList.text = item.riskyActivities
            riskyActivityList.setTextColor(item.textColor)
            card.setCardBackgroundColor(item.backgroundColor)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_risk, parent, false)
                return ViewHolder(view)
            }
        }
    }

    class RiskyActivitiesDiffUtils: DiffUtil.ItemCallback<RiskyActivity>(){

        override fun areItemsTheSame(oldItem: RiskyActivity, newItem: RiskyActivity): Boolean {
           return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RiskyActivity, newItem: RiskyActivity): Boolean {
            return  oldItem == newItem
        }
    }
}