package com.example.bookmanager.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookmanager.bookFragment.BookFragmentViewModel
import com.example.bookmanager.recyclerFragment.RecyclerFragmentViewModel
import com.example.bookmanager.repository.BookRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BookRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecyclerFragmentViewModel::class.java)){
            return RecyclerFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(BookFragmentViewModel::class.java)){
            return BookFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}