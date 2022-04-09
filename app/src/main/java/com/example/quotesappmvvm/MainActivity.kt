package com.example.quotesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotesappmvvm.API.QuotesInterface
import com.example.quotesappmvvm.API.RetrofitHelper
import com.example.quotesappmvvm.Repo.QuotesRepository
import com.example.quotesappmvvm.ViewModel.MainViewModel
import com.example.quotesappmvvm.ViewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container , BlankFragment()).commit()

//        val quotesDao = RetrofitHelper.getInstance().create(QuotesInterface::class.java)
//        val quotesRepo = QuotesRepository(quotesDao)
//        val viewModel = ViewModelProvider(this , MainViewModelFactory(quotesRepo)).get(MainViewModel::class.java)

//        viewModel.quotes.observe(this , Observer {
//            Log.d("checkingAPI" , it.results.toString())
//        })

//        viewModel.quotes.observe(this , Observer { quotesList ->
//            val result = quotesList.results
//            result.forEach {
//                Log.d("quotesTitles" , it.content)
//            }
//        })

    }
}