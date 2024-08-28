package com.dotech.quotes.app.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotech.quotes.data.models.ListQuotesDto
import com.dotech.quotes.data.repositories.QuotesRepo
import com.dotech.quotes.domain.usecases.ResonseListQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ResonseListQuoteUseCase,
    private val repo: QuotesRepo
): ViewModel() {

    private val _responseList = mutableStateOf(QuotesState<ListQuotesDto>())
    val responseList : State<QuotesState<ListQuotesDto>> = _responseList

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
//            when(val result =repo.getRandomQuote().first()){
//                is ResponseState.Success -> _responseList.value = QuotesState(data = result.data)
//                is ResponseState.Error -> _responseList.value = QuotesState(error = result.message ?: "unExpected Error VM")
//                is ResponseState.Loading -> _responseList.value = QuotesState(isLoading = true)
//            }
        }

    }
}