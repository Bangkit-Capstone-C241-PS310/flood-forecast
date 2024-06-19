package com.capstone.floodforecast.view.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.floodforecast.R

data class FaqItem(
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)

class FaqAdapter(private val items: List<FaqItem>) : RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val item = items[position]
        holder.question.text = item.question
        holder.answer.text = item.answer
        holder.answer.visibility = if (item.isExpanded) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }

        holder.expandButton.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size

    class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.question)
        val answer: TextView = itemView.findViewById(R.id.answer)
        val expandButton: ImageButton = itemView.findViewById(R.id.expandButton)
    }
}