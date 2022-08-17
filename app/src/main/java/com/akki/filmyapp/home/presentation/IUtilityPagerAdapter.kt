package com.akki.filmyapp.home.presentation

import com.akki.filmyapp.imageloader.IImageLoader
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface IUtilityPagerAdapter {
    var imageLoader: IImageLoader
}