package com.dotech.quotes.app.di

import android.content.Context
import androidx.room.Room
import com.dotech.quotes.common.Constants
import com.dotech.quotes.data.local.dao.QuoteDao
import com.dotech.quotes.data.local.QuotesDatabase
import com.dotech.quotes.data.remote.QuotesApi
import com.dotech.quotes.data.repositories.QuotesRepo
import com.dotech.quotes.data.repositories.QuotesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService() = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(QuotesApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuotesDatabase =
        Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            QuotesDatabase::class.java.simpleName
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideQuotesDao(database: QuotesDatabase): QuoteDao = database.quoteDao()

    @Provides
    fun provideQuotesRepo(api: QuotesApi): QuotesRepo = QuotesRepoImpl(api)


}