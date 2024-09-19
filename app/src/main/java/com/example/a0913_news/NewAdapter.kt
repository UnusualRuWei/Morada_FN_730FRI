package com.example.a0913_news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class NewsItem(val title: String, val content: String, val imageResId: Int)

class NewsAdapter(
    private val context: Context,
    private val newsItems: List<NewsItem>,
    private val onItemClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.news_title)
        val contentTextView: TextView = itemView.findViewById(R.id.news_content)
        val imageView: ImageView = itemView.findViewById(R.id.news_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(newsItems[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.newslistitem, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsItems[position]
        holder.titleTextView.text = newsItem.title
        holder.contentTextView.text = newsItem.content
        holder.imageView.setImageResource(newsItem.imageResId)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }
}
