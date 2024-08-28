package com.dotech.quotes.data.remote

import com.dotech.quotes.common.Constants.List_Of_quotes
import com.dotech.quotes.common.Constants.random_quotes
import com.dotech.quotes.data.models.QuoteDto
import com.dotech.quotes.data.models.ListQuotesDto
import retrofit2.http.GET

interface QuotesApi {

    @GET(List_Of_quotes)
    suspend fun getListOfQuotes() : ListQuotesDto

    @GET(random_quotes)
    suspend fun getRandomQuotes() : QuoteDto
}