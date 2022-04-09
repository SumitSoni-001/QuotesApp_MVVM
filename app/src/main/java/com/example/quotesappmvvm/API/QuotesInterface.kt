package com.example.quotesappmvvm.API

import com.example.quotesappmvvm.API.Models.QuotesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesInterface {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page : Int) : Response<QuotesList>

}