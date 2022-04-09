package com.example.quotesappmvvm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesappmvvm.API.Models.QuotesList
import com.example.quotesappmvvm.Repo.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepository) : ViewModel() {

    init {  // We will get the quotesList directly when the viewModel is called.
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<QuotesList>
        get() = repository.quotes

}