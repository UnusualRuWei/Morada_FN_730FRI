package com.example.a0913_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContents : Fragment() {

    private lateinit var newsImageView: ImageView
    private lateinit var newsTitleTextView: TextView
    private lateinit var newsContentTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_contents, container, false)

        newsImageView = view.findViewById(R.id.news_image)
        newsTitleTextView = view.findViewById(R.id.news_title)
        newsContentTextView = view.findViewById(R.id.news_content)

        // Retrieve the news item passed as arguments
        val newsItem = arguments?.let {
            // Populate the views with data
            val title = it.getString("title") ?: "No Title"
            val content = it.getString("content") ?: "No Content"
            val imageResId = it.getInt("imageResId")

            view.findViewById<TextView>(R.id.news_title).text = title
            view.findViewById<TextView>(R.id.news_content).text = content
            view.findViewById<ImageView>(R.id.news_image).setImageResource(imageResId)
        }

        return view
    }
}
