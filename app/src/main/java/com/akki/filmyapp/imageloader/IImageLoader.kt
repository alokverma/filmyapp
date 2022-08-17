package com.akki.filmyapp.imageloader

import android.widget.ImageView

interface IImageLoader {

    fun imageLoad(url: String, iv: ImageView)

    fun onSuccessLoad()

    fun onFailure()
}