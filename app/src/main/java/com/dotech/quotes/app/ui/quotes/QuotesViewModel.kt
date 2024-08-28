package com.dotech.quotes.app.ui.quotes

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotech.quotes.app.ui.QuotesState
import com.dotech.quotes.common.Constants
import com.dotech.quotes.data.models.toQuotesList
import com.dotech.quotes.data.repositories.QuotesRepo
import com.dotech.quotes.domain.models.ListQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repo: QuotesRepo,
    @ApplicationContext
    private val context: Context
) : ViewModel() {

    private val _quotesList = mutableStateOf(QuotesState<ListQuotes>())
    val quotesList : MutableState<QuotesState<ListQuotes>> = _quotesList

    init {
        getList()
    }


    private fun getList(){
        if (Constants.isNetworkAvailable(context)){
            viewModelScope.launch {
                try {
                    val list = repo.getListQuotes().first().toQuotesList()
                    _quotesList.value = QuotesState(data = list)
                }catch (e:Exception){
                    _quotesList.value = QuotesState(error = e.message?:"Un known Error")
                }
            }
        }else{
            _quotesList.value = QuotesState(error="No Internet Connection")
        }
    }
}