package com.example.quotesappmvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quotesappmvvm.API.QuotesInterface
import com.example.quotesappmvvm.API.RetrofitHelper
import com.example.quotesappmvvm.DB.QuotesDatabase
import com.example.quotesappmvvm.Repo.QuotesRepository
import com.example.quotesappmvvm.ViewModel.MainViewModel
import com.example.quotesappmvvm.ViewModel.MainViewModelFactory
import com.example.quotesappmvvm.databinding.FragmentBlankBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BlankFragment : Fragment() {

    lateinit var binding: FragmentBlankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBlankBinding.inflate(inflater)
//        binding =  DataBindingUtil.inflate(inflater ,R.layout.fragment_blank, container, false)

//        val quotesInterface = RetrofitHelper.getInstance().create(QuotesInterface::class.java)
//        val quotesDao = QuotesDatabase.getDatabase(activity as MainActivity).quotesDao()
//        val quotesRepo = QuotesRepository(quotesInterface , quotesDao , context as MainActivity)

        val quotesRepo = (activity?.application as QuotesApplication).quotesRepo
        val viewModel =
            ViewModelProvider(this, MainViewModelFactory(quotesRepo)).get(MainViewModel::class.java)

        /** GlobalScope.launch is used if you want to launch a coroutine that is supposed to remain in memory for the entire lifecycle of the app.
         * But since a fragment usually has a shorter lifecycle than the app, GlobalScope.launch probably isn't the proper way.
         * I assume that if I used GlobalScope.launch, it might keep the fragment from being garbage collected
         * So, we will use 'lifeCycleScope' for Fragments*/

//        lifecycleScope.launch { }

        viewModel.quotes.observe(activity as MainActivity, Observer {
            Toast.makeText(activity as MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT)
                .show()
            Log.d("quotesList", it.results.toString())
        })

        /*     viewModel.quotes.observe(activity as MainActivity, Observer { quotesList ->
                 val result = quotesList.results
                 result.forEach {
                     Log.d("quotesTitles", it.content)
                     Log.d("quotesTitles", it.author)
                 }
             })*/

        return binding.root
    }

}