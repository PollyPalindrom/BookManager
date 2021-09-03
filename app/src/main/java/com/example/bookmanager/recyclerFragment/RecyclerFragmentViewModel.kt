package com.example.bookmanager.recyclerFragment

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.bookmanager.MainActivity
import com.example.bookmanager.database.Book
import com.example.bookmanager.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecyclerFragmentViewModel(private val repository: BookRepository) : ViewModel() {


    var books: MutableLiveData<MutableList<Book>> =
        repository.books.asLiveData() as MutableLiveData<MutableList<Book>>

    fun chooseSort(context: Context) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val option = prefs.getString("list_preference", "не установлено")
        when (prefs.getString("list_preference", "не установлено")) {
            "1" -> {
                books.value?.sortBy { it.title }
            }
            "2" -> {
                books.value?.sortBy { it.author }
            }
            "3" -> {
                books.value?.sortBy { it.year }
            }
        }
    }

    fun deleteItem(position: Int, context: Context) {
        val book = books.value?.get(position)
        viewModelScope.launch(Dispatchers.IO) {
            if (book != null && PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean("room_or_cursor", true)
            ) {
                repository.delete(book)
            } else if (book != null) {
                repository.deleteBook(book)
                books.postValue(repository.getAll() as MutableList<Book>?)
            }
        }
    }

    fun chooseSubd(context: Context, mainActivity: MainActivity) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        when (prefs.getBoolean("room_or_cursor", true)) {
            false -> {
                viewModelScope.launch(Dispatchers.IO) {
                    books.postValue(repository.getAll() as MutableList<Book>?)
                }
                mainActivity.changeSubtitle("Cursor")
            }
            else -> mainActivity.changeSubtitle("Room")
        }

    }
}