package com.dotech.quotes.app.di

import com.dotech.quotes.common.Constants
import com.dotech.quotes.data.remote.QuotesApi
import com.dotech.quotes.data.repositories.QuotesRepo
import com.dotech.quotes.data.repositories.QuotesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuotesApi::class.java)

    @Provides
    fun provideQuotesRepo(api:QuotesApi) : QuotesRepo = QuotesRepoImpl(api)
}