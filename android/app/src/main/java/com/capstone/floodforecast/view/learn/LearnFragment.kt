package com.capstone.floodforecast.view.learn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.floodforecast.R
import com.capstone.floodforecast.util.Constant

class LearnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = LearnAdapter(Constant.SAMPLE_DATA_ARTICLE) { item ->
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_AUTHOR", item.author)
                putExtra("EXTRA_TEXT_ARTICLE", item.textArticle)
                putExtra("EXTRA_COVER", item.cover)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        return view
    }
}