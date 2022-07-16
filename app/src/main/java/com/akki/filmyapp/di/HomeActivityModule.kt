package com.akki.filmyapp.di

import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.home.data.repository.HomeRepositoryImpl
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class HomeActivityModule {

    @Provides
    fun provideHomeRepository(apiLogger: ILogger, apiService: ApiInterface): IHomeRepository {
        return HomeRepositoryImpl(apiService, apiLogger)
    }
}