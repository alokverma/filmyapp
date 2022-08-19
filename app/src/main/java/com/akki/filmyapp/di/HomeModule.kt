package com.akki.filmyapp.di

import android.app.Activity
import android.content.Context
import com.akki.filmyapp.home.presentation.PagerImageAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
object HomeModule {

    @Provides
    @FragmentScoped
    fun providePagerAdapter(
        @ActivityContext context: Context,
    ): PagerImageAdapter {
        return PagerImageAdapter(context)
    }

    // @fixme
//    @Provides
//    @FragmentScoped
//    fun provideMoviePagerAdapter(
//        @ActivityContext context: Context,
//    ): MoviePagerAdapter {
//        return MoviePagerAdapter((context as Activity))
//    }
}