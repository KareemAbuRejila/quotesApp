package com.dotech.quotes.data.repositories

import com.dotech.quotes.data.models.QuoteDto
import com.dotech.quotes.data.remote.QuotesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuotesRepoImpl @Inject constructor(private val api: QuotesApi) : QuotesRepo {
    override suspend fun getListQuotes() = flow {
        val response = api.getListOfQuotes()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun getRandomQuote(): Flow<QuoteDto> = flow {
        val response = api.getRandomQuotes()
        emit(response)
    }.flowOn(Dispatchers.IO)


}