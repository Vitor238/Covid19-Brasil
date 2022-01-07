package com.vitor238.covid19brasil.presentation.usefullinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitor238.covid19brasil.data.domain.UsefulLink
import com.vitor238.covid19brasil.databinding.ItemLinksBinding

class AdapterLinks(private val onClickListener: ((usefulLink: UsefulLink) -> Unit)) :
    ListAdapter<UsefulLink, AdapterLinks.ViewHolder>(LinksDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class ViewHolder(binding: ItemLinksBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageThumbnail: ImageView = binding.imageThumbnail
        private val textTitle: TextView = binding.textTitle
        private val textAuthor: TextView = binding.textAuthor

        fun bind(link: UsefulLink, onClickListener: ((usefulLink: UsefulLink) -> Unit)) {

            textTitle.text = textTitle.context.getString(link.title)

            link.author?.let {
                textAuthor.visibility = View.VISIBLE
                textAuthor.text = textAuthor.context.getString(link.author)
            }

            Glide.with(imageThumbnail.context).load(link.thumbnail).into(imageThumbnail)

            itemView.setOnClickListener {
                onClickListener.invoke(link)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemLinksBinding.inflate(LayoutInflater.from(parent.context), parent,
                    false)
                return ViewHolder(binding)
            }
        }
    }

    class LinksDiffUtils : DiffUtil.ItemCallback<UsefulLink>() {
        override fun areItemsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean {
            return oldItem == newItem
        }
    }
}