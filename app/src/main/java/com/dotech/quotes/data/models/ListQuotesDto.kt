package com.dotech.quotes.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.dotech.quotes.domain.models.ListQuotes
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "list_table")
data class ListQuotesDto(
    @SerializedName("count") val count: Int= 0,
    @SerializedName("lastItemIndex") val lastItemIndex: Int= 0,
    @SerializedName("page") val page: Int= 0,
    @SerializedName("results") val quotesResults: List<QuoteDto>,
    @SerializedName("totalCount") val totalCount: Int= 0,
    @SerializedName("totalPages") val totalPages: Int= 0
) : Parcelable


fun ListQuotesDto.toQuotesList() = ListQuotes(
    count = count,
    lastItemIndex = lastItemIndex,
    page = page,
    quotes = quotesResults.map { it.toQuote() },
    totalCount = totalCount,
    totalPages = totalPages
)
