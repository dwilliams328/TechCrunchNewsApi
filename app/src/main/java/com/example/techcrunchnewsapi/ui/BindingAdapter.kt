package com.example.techcrunchnewsapi.ui

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("setImageUrl")
        fun setImageUrl(view: ImageView, url: String?) {
            ContextCompat.getDrawable(view.context, com.google.android.material.R.drawable.design_password_eye)
                ?.let {
                    Picasso.get()
                        .load(url)
                        .placeholder(it)
                        .into(view)
                }
        }

    }
}