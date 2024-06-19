package com.capstone.floodforecast.view.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.floodforecast.R

data class LearnItem(val title: String, val author: String, val textArticle: String, val cover: Int)

class LearnAdapter(
    private val items: List<LearnItem>,
    private val itemClickListener: (LearnItem) -> Unit
) : RecyclerView.Adapter<LearnAdapter.LearnViewHolder>() {

    class LearnViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitle)
        val author: TextView = itemView.findViewById(R.id.textViewAuthor)
        val cover: ImageView = itemView.findViewById(R.id.cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.learn_card, parent, false)
        return LearnViewHolder(view)
    }

    override fun onBindViewHolder(holder: LearnViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.author.text = item.author
        Glide.with(holder.itemView)
            .load(item.cover)
            .into(holder.cover)
        holder.itemView.setOnClickListener { itemClickListener(item) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}