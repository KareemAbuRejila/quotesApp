package com.dotech.quotes.data.models

import android.os.Parcelable
import com.dotech.quotes.domain.models.Quote
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteDto(
    @SerializedName("author") val author: String,
    @SerializedName("authorSlug") val authorSlug: String,
    @SerializedName("content") val content: String,
    @SerializedName("dateAdded") val dateAdded: String,
    @SerializedName("dateModified") val dateModified: String,
    @SerializedName("_id") val id: String,
    @SerializedName("length") val length: Int,
    @SerializedName("tags") val tags: List<String>,
) : Parcelable

fun QuoteDto.toQuote() = Quote(
    author=author,content =content,dateAdded=dateAdded
)
