package com.dotech.quotes.domain.usecases

import android.util.Log
import com.dotech.quotes.app.ui.QuotesState
import com.dotech.quotes.common.ResponseState
import com.dotech.quotes.data.models.toQuote
import com.dotech.quotes.data.repositories.QuotesRepo
import com.dotech.quotes.domain.models.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RandomQuoteUseCase @Inject constructor(
    private val repo: QuotesRepo
) {

    operator fun invoke(): Flow<ResponseState<Quote>> = flow {
        try {
            emit(ResponseState.Loading())
            val quote = repo.getRandomQuote().first().toQuote()
            emit(ResponseState.Success(quote))
        }
        catch (e: HttpException) {
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error Msg domain",null))
        }catch (e: IOException){
            emit(ResponseState.Error("Couldn't reach server. Check your internet Connection",null))

        }
    }
}