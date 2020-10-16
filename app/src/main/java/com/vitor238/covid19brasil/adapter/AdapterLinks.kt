package com.vitor238.covid19brasil.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.model.UsefulLink


class AdapterLinks(val context: Context,
                   val listLinks: List<UsefulLink>,
                   val onLinkClickListener: OnLinkClickListener) :
    RecyclerView.Adapter<AdapterLinks.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_links, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLinks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val link = listLinks[position]
        holder.bind(link,onLinkClickListener)
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val imageThumbnail: RoundedImageView = item.findViewById(R.id.imageThumbnail)
        val textTitle: TextView = item.findViewById(R.id.textTitle)
        val textAuthor: TextView = item.findViewById(R.id.textAuthor)

        fun bind(link:UsefulLink,onLinkClickListener: OnLinkClickListener){
            textAuthor.text = link.author
            textTitle.text = link.title

            Glide.with(context).load(link.thumbnail).into(imageThumbnail)

            itemView.setOnClickListener {
                onLinkClickListener.onLinkClick(link)
            }

        }
    }

    interface OnLinkClickListener{
        fun onLinkClick(usefulLink: UsefulLink)
    }
}