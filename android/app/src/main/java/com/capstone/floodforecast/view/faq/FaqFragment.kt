package com.capstone.floodforecast.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstone.floodforecast.R
import com.capstone.floodforecast.databinding.FragmentFaqBinding

class FaqFragment : Fragment() {

    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaqBinding.inflate(inflater, container, false)

        setupAccordion(binding.root)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupAccordion(view: View) {
        val expandButton1: ImageButton = view.findViewById(R.id.expandButton1)
        val answer1: TextView = view.findViewById(R.id.answer1)

        val expandButton2: ImageButton = view.findViewById(R.id.expandButton2)
        val answer2: TextView = view.findViewById(R.id.answer2)

        val expandButton3: ImageButton = view.findViewById(R.id.expandButton3)
        val answer3: TextView = view.findViewById(R.id.answer3)

        expandButton1.setOnClickListener {
            if (answer1.visibility == View.GONE) {
                answer1.visibility = View.VISIBLE
            } else {
                answer1.visibility = View.GONE
            }
        }

        expandButton2.setOnClickListener {
            if (answer2.visibility == View.GONE) {
                answer2.visibility = View.VISIBLE
            } else {
                answer2.visibility = View.GONE
            }
        }

        expandButton3.setOnClickListener {
            if (answer3.visibility == View.GONE) {
                answer3.visibility = View.VISIBLE
            } else {
                answer3.visibility = View.GONE
            }
        }
    }
}
