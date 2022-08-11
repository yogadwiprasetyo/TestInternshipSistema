package com.yogaprasetyo.testinternshipsistema.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

/**
 * Load image from url with Glide library
 * */
fun ImageView.loadImage(context: Context, source: Any?) {
    Glide.with(context)
        .load(source)
        .into(this)
}

/**
 * Merge to string with spacing
 * */
fun String.merge(next: String): String {
    return "$this $next"
}