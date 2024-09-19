package com.example.a0913_news

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_news_list, container, false)

        val listView = view.findViewById<RecyclerView>(R.id.newsListView)
        val newsItems = ArrayList<NewsItem>()

        // Sample data
        newsItems.add(NewsItem("Breaking News 1", "This is the content for news 1", R.drawable.shiken))
        newsItems.add(NewsItem("Breaking News 2", "This is the content for news 2", R.drawable.shiken))
        newsItems.add(NewsItem("Breaking News 3", "This is the content for news 3", R.drawable.shiken))

        val adapter = NewsAdapter(view.context, newsItems) { newsItem ->
            showNewsDetails(newsItem)
        }

        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.adapter = adapter

        return view
    }

    private fun showNewsDetails(newsItem: NewsItem) {
        val bundle = Bundle().apply {
            putString("title", newsItem.title)
            putString("content", newsItem.content)
            putInt("imageResId", newsItem.imageResId)
        }

        val newsContentsFragment = NewsContents().apply {
            arguments = bundle
        }

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Replace newList with NewsContentsFragment in portrait mode
            parentFragmentManager.beginTransaction()
                .replace(R.id.newList, newsContentsFragment)
                .addToBackStack(null)
                .commit()
        } else {

            parentFragmentManager.beginTransaction()
                .replace(R.id.newListContent, newsContentsFragment)
                .addToBackStack(null)
                .commit()
        }

    }


}
