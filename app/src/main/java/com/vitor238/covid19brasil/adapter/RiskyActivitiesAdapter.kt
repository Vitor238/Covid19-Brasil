package com.vitor238.covid19brasil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.model.RiskyActivity
import kotlinx.android.synthetic.main.item_risk.view.*

class RiskyActivitiesAdapter : RecyclerView.Adapter<RiskyActivitiesAdapter.ViewHolder>() {

    private var riskyActivitiesList = listOf<RiskyActivity>()

    fun setListData(data: List<RiskyActivity>) {
        riskyActivitiesList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(riskyActivitiesList[position])
    }

    override fun getItemCount(): Int {
        return riskyActivitiesList.size
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
}