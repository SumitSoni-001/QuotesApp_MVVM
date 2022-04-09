package com.example.quotesappmvvm.Repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesappmvvm.API.Models.QuotesList
import com.example.quotesappmvvm.API.QuotesInterface
import com.example.quotesappmvvm.DB.Quotes
import com.example.quotesappmvvm.DB.QuotesDAO
import com.example.quotesappmvvm.utils.Network

class QuotesRepository(
    private val quotesInterface: QuotesInterface,
    private val quotesDAO: QuotesDAO,
    private val context: Context
) {

    private val quotesLiveData =
        MutableLiveData<QuotesList>()  // It will get the data from apiInterface using getQuotes() method.

    val quotes: LiveData<QuotesList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (Network.checkForInternet(context)) {
            /** Internet is ON */
            val result = quotesInterface.getQuotes(page)
            if (result.body() != null) {
                quotesDAO.addQuotes(result.body()!!.results)
                /** Data from api will be added to Room Database */
                quotesLiveData.postValue(result.body())
            }
        } else {
            /** Internet is OFF */
            val result = quotesDAO.getQuotes()
//            quotesLiveData.postValue(result) /** we can't pass result in quotesLiveData, we have to make object of QuotesList class and then pass it in quotesLiveData. */
            val quotesList = QuotesList(1, 1, 1, result, 1, 1)
            quotesLiveData.postValue(quotesList)
        }
    }

}