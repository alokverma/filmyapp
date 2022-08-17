package com.akki.filmyapp.di

import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.home.data.repository.HomeRepositoryImpl
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(apiLogger: ILogger, apiService: ApiInterface): IHomeRepository {
        return HomeRepositoryImpl(apiService, apiLogger)
    }
}