package com.dotech.quotes.app.ui.quotes

import androidx.lifecycle.ViewModel
import com.dotech.quotes.data.repositories.QuotesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val repo: QuotesRepo
) : ViewModel() {
}