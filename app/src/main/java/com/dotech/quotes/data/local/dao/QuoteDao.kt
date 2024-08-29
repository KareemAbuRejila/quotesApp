package com.dotech.quotes.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dotech.quotes.data.models.QuoteDto

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quoteDto: QuoteDto)

    @Update
    suspend fun update(quoteDto: QuoteDto)

    @Delete
    suspend fun delete(quoteDto: QuoteDto)

    @Query("SELECT * FROM quotes_table")
    fun getAll(): LiveData<List<QuoteDto>>
}