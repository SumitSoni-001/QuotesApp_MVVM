package com.example.quotesappmvvm

import android.app.Application
import com.example.quotesappmvvm.API.QuotesInterface
import com.example.quotesappmvvm.API.RetrofitHelper
import com.example.quotesappmvvm.DB.QuotesDatabase
import com.example.quotesappmvvm.Repo.QuotesRepository

/** This class will contain all the objects which are needed allOver the Application. */
class QuotesApplication : Application() {

    lateinit var quotesRepo: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quotesInterface = RetrofitHelper.getInstance().create(QuotesInterface::class.java)
        val quotesDao = QuotesDatabase.getDatabase(applicationContext).quotesDao()
        quotesRepo = QuotesRepository(quotesInterface, quotesDao, applicationContext)
    }

}