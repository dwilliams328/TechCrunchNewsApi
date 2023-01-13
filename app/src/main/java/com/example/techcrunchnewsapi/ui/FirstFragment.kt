package com.example.techcrunchnewsapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.techcrunchnewsapi.R
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var _binding: FragmentFirstBinding
    private lateinit var bundle: Bundle
    private val viewModel: MainViewModel by activityViewModels()


    private var newsClickListener = object : NewsAdapter.NewsClickListener {
        override fun onClick(newsArticle: NewsArticle) {
            Log.d("abc", "view clicked: ${newsArticle.title}")
            bundle = bundleOf("newsItem" to newsArticle)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val adapter = NewsAdapter(newsClickListener)

        _binding.rvFirst.adapter = adapter
        adapter.setNewsItems(viewModel.newsArticle.value) // TODO: Data loading, but ui not updating. Reproduce -> navigate 2nd Fragment & back to 1st Fragment

        _binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_RegisterFragment)
        }

        _binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_LoginFragment)
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