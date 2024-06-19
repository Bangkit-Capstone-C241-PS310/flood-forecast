package com.capstone.floodforecast.view.learn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.floodforecast.R

class LearnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Contoh data untuk diisi ke dalam adapter
        val sampleData = listOf(
            LearnItem("Title 1", "Author 1"),
            LearnItem("Title 2", "Author 2"),
            LearnItem("Title 3", "Author 3")
        )
        val adapter = LearnAdapter(sampleData)
        recyclerView.adapter = adapter

        return view
    }
}