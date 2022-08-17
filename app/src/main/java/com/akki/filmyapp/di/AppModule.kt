package com.akki.filmyapp.di

import android.content.Context
import com.akki.filmyapp.BuildConfig
import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.api.Constants
import com.akki.filmyapp.imageloader.IImageLoader
import com.akki.filmyapp.imageloader.ImageLoaderWrapper
import com.akki.filmyapp.logging.ILogger
import com.akki.filmyapp.logging.LoggerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

//    @Provides
//    fun provideApiKey() = Constants.API_KEY


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideLoggerService(): ILogger = LoggerService()

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext appContext: Context): IImageLoader =
        ImageLoaderWrapper(appContext)
}