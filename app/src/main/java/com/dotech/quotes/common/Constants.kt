package com.dotech.quotes.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.dotech.quotes.domain.models.ListQuotes
import com.dotech.quotes.domain.models.Quote

object Constants {

    const val BASE_URL = "https://api.quotable.io/"
    const val List_Of_quotes = "quotes?limit=20"
    const val random_quotes = "random?maxLength=150&minLength=100"


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
        connectivityManager?.let {
            val networkCapabilities = it.getNetworkCapabilities(it.activeNetwork)
            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false
    }

    fun getQuote() = Quote (
        id = "Z5FIvoa4U1f-",
        content = "We must be as courteous to a man as we are to a picture, which we are willing to give the advantage of a good light.",
        author = "Ralph Waldo Emerson",
        tags = "Famous Quotes",
        authorSlug = "ralph-waldo-emerson",
        length = "116",
        dateAdded = "2019-12-23",
        dateModified = "2023-04-14"
    )
    fun list()=listOf(getQuote(), getQuote().copy(author = "Ralph Waldo"), getQuote().copy(author = "Ralph"))
    fun getListQuotes() = ListQuotes(count = list().size, lastItemIndex = list().lastIndex, page = 0, quotes = list(),totalCount=list().size)
}