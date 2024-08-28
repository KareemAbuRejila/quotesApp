package com.dotech.quotes.data.repositories

import com.dotech.quotes.data.models.QuoteDto
import com.dotech.quotes.data.models.ListQuotesDto
import kotlinx.coroutines.flow.Flow

interface QuotesRepo {

    suspend fun getListQuotes() : Flow<ListQuotesDto>
    suspend fun getRandomQuote(): Flow<QuoteDto>
}