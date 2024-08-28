package com.dotech.quotes.app.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotech.quotes.common.Constants.isNetworkAvailable
import com.dotech.quotes.common.ResponseState
import com.dotech.quotes.domain.models.Quote
import com.dotech.quotes.domain.usecases.RandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dotech.quotes.app.ui.QuotesState
import com.dotech.quotes.common.Constants
import com.dotech.quotes.data.models.toQuote
import com.dotech.quotes.data.repositories.QuotesRepo
import kotlinx.coroutines.flow.first


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: RandomQuoteUseCase,
    private val repo: QuotesRepo,
    @ApplicationContext
    private val context: Context
): ViewModel() {


    private val _stateQuote = mutableStateOf(QuotesState<Quote>())
    val stateQuote : State<QuotesState<Quote>> = _stateQuote
//    val stateQuote : State<QuotesState<Quote>> = mutableStateOf(QuotesState(data = Constants.getQuote()))

    init {
        fetchRandomQuote()
    }

    fun fetchRandomQuote() {
        viewModelScope.launch {
            if (isNetworkAvailable(context)){
                try {
//                useCase().onEach {result->
//                    when(result){
//                        is ResponseState.Success -> _stateQuote.value = QuotesState(data = result.data)
//                        is ResponseState.Error -> _stateQuote.value = QuotesState(error = result.message ?: "unExpected Error VM")
//                        is ResponseState.Loading -> _stateQuote.value = QuotesState(isLoading = true)
//                    }
//                    Log.i("RandomQuote", _stateQuote.value.data?.content?:"Nothing")
//                }
                    val quote = repo.getRandomQuote().first().toQuote()
                    _stateQuote.value = QuotesState(data = quote)
                                    }
                catch (e: Exception) {
                    val errorMessage = "An error occurred. Please try again."
                    _stateQuote.value = QuotesState(error = errorMessage ?: "unExpected Error VM")
                }
            }else{
                _stateQuote.value = QuotesState(error="No Internet Connection")
            }
        }
    }
}