package com.dotech.quotes.domain.usecases

import com.dotech.quotes.common.ResponseState
import com.dotech.quotes.data.models.ListQuotesDto
import com.dotech.quotes.data.repositories.QuotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ResonseListQuoteUseCase @Inject constructor(
    private val repo: QuotesRepo
) {

    operator fun invoke(): Flow<ResponseState<ListQuotesDto>> = flow {
        try {
            emit(ResponseState.Loading())
            val quote = repo.getListQuotes().first()
            emit(ResponseState.Success(quote))
        }catch (e: HttpException) {
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error Msg domain",null))
        }catch (e: IOException){
            emit(ResponseState.Error("Couldn't reach server. Check your internet Connection",null))

        }
    }
}