package com.example.jakewhartongithubrepositories.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.jakewhartongithubrepositories.R

fun loadImage(context: Context, imageUrl: String, imageView: ImageView) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.image_placeholder)
        .into(imageView)
}