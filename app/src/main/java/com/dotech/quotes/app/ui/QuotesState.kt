package com.dotech.quotes.app.ui

import com.dotech.quotes.domain.usecases.ResonseListQuoteUseCase

data class QuotesState<out T>(
    val isLoading: Boolean = false,
    val data: T?= null,
    val error: String = ""
)
