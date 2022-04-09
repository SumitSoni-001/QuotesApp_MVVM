package com.example.quotesappmvvm.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quotesappmvvm.API.Models.Result

@Dao
interface QuotesDAO {

    @Insert
    fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quotes")
    fun getQuotes() : List<Result>

}