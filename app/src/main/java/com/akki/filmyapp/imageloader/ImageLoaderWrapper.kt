package com.akki.filmyapp.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class ImageLoaderWrapper @Inject constructor(private val context: Context) : IImageLoader {

    override fun imageLoad(url: String, iv: ImageView) {
        Glide.with(context).load(url).into(iv)
    }

    override fun onSuccessLoad() {
    }

    override fun onFailure() {
    }
}