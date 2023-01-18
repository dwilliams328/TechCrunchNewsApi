package com.example.techcrunchnewsapi.ui.newsarticles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.databinding.ItemViewArticleBinding

class NewsAdapter(private val newsClickListener: NewsClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var uiNewsListData: List<NewsArticle> = mutableListOf()
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
        holder.bind(uiNewsListData[position], newsClickListener)
    }

    override fun getItemCount(): Int {
        return uiNewsListData.size
    }

    fun setNewsList(newsList: List<NewsArticle>) {
        uiNewsListData = newsList
        notifyDataSetChanged()
    }

    interface NewsClickListener {
        fun onClick(newsArticle: NewsArticle)
    }
}