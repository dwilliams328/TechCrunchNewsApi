package com.example.techcrunchnewsapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.databinding.ItemViewArticleBinding

class NewsAdapter(private val newsClickListener: NewsClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var newsList: List<NewsArticle> = mutableListOf(
        NewsArticle(
            "David",
            "Title1",
            "desc of title1",
            "https://techcrunch.com/wp-content/uploads/2022/04/tiktok-header.webp"
        )
    )
    private lateinit var binding: ItemViewArticleBinding

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(newsArticle: NewsArticle, clickListener: NewsClickListener) {
            binding.newArticle = newsArticle
            binding.cardViewItem.setOnClickListener {
                clickListener.onClick(newsArticle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemViewArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position], newsClickListener)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsItems(newsItem: List<NewsArticle>){
        newsList = newsItem
        notifyDataSetChanged()
    }

    interface NewsClickListener {
        fun onClick(newsArticle: NewsArticle)
    }
}