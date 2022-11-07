package com.akki.filmyapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object HomeModule {

    // @fixme
//    @Provides
//    @FragmentScoped
//    fun provideMoviePagerAdapter(
//        @ActivityContext context: Context,
//    ): MoviePagerAdapter {
//        return MoviePagerAdapter((context as Activity))
//    }
}
