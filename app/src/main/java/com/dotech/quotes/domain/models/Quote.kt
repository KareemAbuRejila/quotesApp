package com.dotech.quotes.domain.models


data class Quote(
    val author: String,
    val authorSlug: String?=null,
    val content: String,
    val dateAdded: String,
    val dateModified: String?=null,
    val id: String?=null,
    val length: String?=null,
    val tags: String?=null
)
