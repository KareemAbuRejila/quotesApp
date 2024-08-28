package com.dotech.quotes.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ListQuotes(
    val count: Int= 0,
    val lastItemIndex: Int= 0,
    val page: Int= 0,
    val quotes: List<Quote>,
    val totalCount: Int= 0,
    val totalPages: Int= 0
)
