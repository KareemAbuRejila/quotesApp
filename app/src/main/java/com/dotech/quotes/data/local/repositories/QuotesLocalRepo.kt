package com.dotech.quotes.data.local.repositories

import androidx.lifecycle.LiveData
import com.dotech.quotes.data.local.dao.QuoteDao
import com.dotech.quotes.data.models.QuoteDto
import com.dotech.quotes.domain.models.Quote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLocalRepo @Inject constructor(private val quoteDao: QuoteDao) {

    val allQuotes: LiveData<List<QuoteDto>> = quoteDao.getAll()

    suspend fun insert(quote: QuoteDto) = quoteDao.insert(quote)
    suspend fun update(quote: QuoteDto) = quoteDao.update(quote)
    suspend fun delete(quote: QuoteDto) = quoteDao.delete(quote)
}