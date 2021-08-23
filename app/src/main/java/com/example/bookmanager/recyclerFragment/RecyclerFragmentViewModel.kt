package com.example.bookmanager.recyclerFragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.bookmanager.R
import com.example.bookmanager.database.AppDatabase
import com.example.bookmanager.database.Book
import com.example.bookmanager.database.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecyclerFragmentViewModel : ViewModel() {


    val books = MutableLiveData<List<Book>>()
    lateinit var dao: BookDao

    fun chooseSort(context: Context, resources: Resources) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val option = prefs.getString("list_preference", "не установлено")

        //TODO: fix strings and shared preferences
        when (option) {
            resources.getString(R.string.first_option) -> {
                books.postValue(books.value?.sortedBy { it.title })
            }
            resources.getString(R.string.second_option) -> {
                books.postValue(books.value?.sortedBy { it.author })
            }
            resources.getString(R.string.third_option) -> {
                books.postValue(books.value?.sortedBy { it.year })
            }
        }
    }

    fun loadListFromDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO: move to repository
            dao = AppDatabase.getInstance(context).stopwatchDao()
            val bookssss = dao.getAll()
            books.postValue(bookssss)
        }
    }

    fun deleteItem(position: Int) {
        val book = books.value?.get(position)
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(book)
            books.postValue(dao.getAll())

        }
    }
}