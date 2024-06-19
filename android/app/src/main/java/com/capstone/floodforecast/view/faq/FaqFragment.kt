package com.capstone.floodforecast.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.floodforecast.R

class FaqFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faq, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Contoh data untuk diisi ke dalam adapter
        val sampleData = listOf(
            FaqItem("What is Raincoat?",
                "Raincoat is an advanced flood forecasting application designed to predict floods in Indonesia. Unlike traditional weather forecasts, Raincoat's sophisticated model analyzes a wide range of climate indicators to provide precise and reliable flood warnings. This allows users to receive accurate warnings about potential floods in their area, helping them take preventive measures to ensure safety. Raincoat aims to significantly reduce flood-related losses by providing early warnings and accurate predictions."
            ),
            FaqItem("What types of data are utilized to train the predictive model in Raincoat?",
                "Raincoat uses a comprehensive dataset enriched with advanced machine learning algorithms to ensure high prediction accuracy. The three key climate indicators analyzed by our model include precipitation levels, temperature, and wind speed. By analyzing these indicators, we can identify trends and anomalies that signal potential flood events."
            ),
            FaqItem("Who is the team behind Raincoat?",
                "The Raincoat project is a collaborative effort by the C241-PS310 team at Bangkit Academy by Google, GoTo, Traveloka, featuring Thalia Fortuna and Irvan Dhimas Maulana on Android development; Alifia Nadiya Putri, Muhammad Daryl Fauzan, and Isnayni Feby Hawari on machine learning, as well as; Alfredo Austin and Yesaya Pasaribu on cloud computing. Our team is driven by the pressing issue of frequent flooding in Indonesia, particularly in Jakarta, and we are committed to raise awareness for a more sustainable future."
            )
        )
        val adapter = FaqAdapter(sampleData)
        recyclerView.adapter = adapter

        return view
    }

//    private var _binding: FragmentFaqBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentFaqBinding.inflate(inflater, container, false)
//
//        setupAccordion(binding.root)
//
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//
//    private fun setupAccordion(view: View) {
//        val expandButton1: ImageButton = view.findViewById(R.id.expandButton1)
//        val answer1: TextView = view.findViewById(R.id.answer1)
//
//        val expandButton2: ImageButton = view.findViewById(R.id.expandButton2)
//        val answer2: TextView = view.findViewById(R.id.answer2)
//
//        val expandButton3: ImageButton = view.findViewById(R.id.expandButton3)
//        val answer3: TextView = view.findViewById(R.id.answer3)
//
//        expandButton1.setOnClickListener {
//            if (answer1.visibility == View.GONE) {
//                answer1.visibility = View.VISIBLE
//            } else {
//                answer1.visibility = View.GONE
//            }
//        }
//
//        expandButton2.setOnClickListener {
//            if (answer2.visibility == View.GONE) {
//                answer2.visibility = View.VISIBLE
//            } else {
//                answer2.visibility = View.GONE
//            }
//        }
//
//        expandButton3.setOnClickListener {
//            if (answer3.visibility == View.GONE) {
//                answer3.visibility = View.VISIBLE
//            } else {
//                answer3.visibility = View.GONE
//            }
//        }
//    }
}
