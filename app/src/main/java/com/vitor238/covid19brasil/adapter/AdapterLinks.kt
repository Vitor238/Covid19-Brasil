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
import com.vitor238.covid19brasil.model.UsefulLink
import kotlinx.android.synthetic.main.item_links.view.*


class AdapterLinks(private val onClickListener:((usefulLink:UsefulLink) -> Unit)) :
    ListAdapter<UsefulLink,AdapterLinks.ViewHolder>(LinksDiffUtils()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),onClickListener)
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        private val imageThumbnail: ImageView = item.image_thumbnail
        private val textTitle: TextView = item.text_title
        private val textAuthor: TextView = item.text_author

        fun bind(link:UsefulLink, onClickListener:((usefulLink:UsefulLink) -> Unit)){
            textAuthor.text = link.author
            textTitle.text = link.title

            Glide.with(imageThumbnail.context).load(link.thumbnail).into(imageThumbnail)

            itemView.setOnClickListener {
                onClickListener.invoke(link)
            }
        }
        
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_links, parent,
                    false
                )
                return ViewHolder(view)
            }
        }
    }
    
    class LinksDiffUtils: DiffUtil.ItemCallback<UsefulLink>(){
        override fun areItemsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean {
            return  oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean {
            return oldItem == newItem
        }
    }
}