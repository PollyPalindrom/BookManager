package com.example.bookmanager.sortFragment

import androidx.lifecycle.ViewModel
import com.example.bookmanager.repository.BookRepository

class SortFragmentViewModel(private val repository: BookRepository) : ViewModel() {
    fun changeState(newValue: Boolean) {
        repository.state = newValue
    }
}