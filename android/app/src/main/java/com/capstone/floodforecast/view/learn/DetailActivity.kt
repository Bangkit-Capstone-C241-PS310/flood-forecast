package com.capstone.floodforecast.view.learn

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.floodforecast.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Ambil data dari intent
        val title = intent.getStringExtra("EXTRA_TITLE")
        val author = intent.getStringExtra("EXTRA_AUTHOR")
        val textArticle = intent.getStringExtra("EXTRA_TEXT_ARTICLE") // Ambil text_article

        // Tampilkan data
        findViewById<TextView>(R.id.titleArticle).text = title
        findViewById<TextView>(R.id.author_article).text = author
        findViewById<TextView>(R.id.text_article).text = textArticle // Tampilkan text_article
    }
}
