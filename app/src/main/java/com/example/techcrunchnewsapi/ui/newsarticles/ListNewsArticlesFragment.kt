package com.example.techcrunchnewsapi.ui.newsarticles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.techcrunchnewsapi.R
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.databinding.FragmentListNewsArticlesBinding
import com.example.techcrunchnewsapi.ui.stateholders.MainViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListNewsArticlesFragment : Fragment() {

    private lateinit var _binding: FragmentListNewsArticlesBinding
    private lateinit var newsArticles: MutableList<NewsArticle>

    private val viewModel: MainViewModel by activityViewModels()

    // Navigate to selected NewsArticle fragment
    private var newsClickListener = object : NewsAdapter.NewsClickListener {
        override fun onClick(newsArticle: NewsArticle) {
            Log.d("abc", "view clicked: ${newsArticle.title}")
            val bundle = bundleOf("newsItem" to newsArticle)
            findNavController().navigate(R.id.action_ListNewsArticlesFragment_to_NewsArticleFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNewsArticlesBinding.inflate(inflater, container, false)
        val adapter = NewsAdapter(newsClickListener)
        _binding.rvFirst.adapter = adapter

        viewModel.newsArticle.observe(viewLifecycleOwner) {
            //TODO "Show loading spinner until data is available"
            if (it == null || it.isEmpty()) {
                _binding.progressBar.visibility = View.VISIBLE
            } else {
                _binding.progressBar.visibility = View.GONE
            }

            adapter.setNewsList(it)
            newsArticles = it.toMutableList()
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val deletedItem = newsArticles[position]

                viewModel.removeArticleAt(position)
                adapter.notifyItemRemoved(position) // TODO dataset update not working as expected
//                adapter.notifyDataSetChanged()

                Snackbar.make(_binding.rvFirst, deletedItem.title, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo"
                    ) {
                        viewModel.addArticleAt(position, deletedItem)
                        adapter.notifyItemInserted(position) // TODO dataset update not working as expected
//                        adapter.notifyDataSetChanged()
                    }.show()
            }

        }).attachToRecyclerView(_binding.rvFirst)

        _binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_ListNewsArticlesFragment_to_RegisterFragment)
        }

        _binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_ListNewsArticlesFragment_to_LoginFragment)
        }

        return _binding.root
    }

    // TODO: Domain & Data layer. Mocking for UI validations
    private fun mockNewsData(): List<NewsArticle> {
        return mutableListOf(
            NewsArticle(
                "David",
                "Title1",
                "desc of title1",
                "https://techcrunch.com/wp-content/uploads/2022/04/tiktok-header.webp"
            ),
            NewsArticle(
                "David",
                "Title2",
                "desc of title2",
                "https://techcrunch.com/wp-content/uploads/2022/05/GettyImages-1201955892.jpeg"
            )
        )
    }
}