package com.dotech.quotes.data.local

import androidx.room.RoomDatabase
import com.dotech.quotes.data.local.dao.QuoteDao

abstract class QuotesDatabase : RoomDatabase(){
    abstract fun quoteDao() : QuoteDao
}