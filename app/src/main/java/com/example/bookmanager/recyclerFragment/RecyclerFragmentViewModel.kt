package com.example.bookmanager.recyclerFragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.bookmanager.repository.BookRepository
import com.example.bookmanager.database.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecyclerFragmentViewModel(private val repository: BookRepository) : ViewModel() {


    val books: LiveData<MutableList<Book>> = repository.books.asLiveData()

    fun chooseSort(context: Context, resources: Resources) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val option = prefs.getString("list_preference", "не установлено")
        //TODO: fix strings and shared preferences
        when (option) {
            "1" -> {
                books.value?.sortBy { it.title }
            }
            "2" -> {
                books.value?.sortBy { it.author }
            }
            "3" -> {
                books.value?.sortBy { it.year.toInt() }
            }
        }
    }

    fun deleteItem(position: Int) {
        val book = books.value?.get(position)
        viewModelScope.launch(Dispatchers.IO) {
            if (book != null) {
                repository.delete(book)
            }

        }
    }
}