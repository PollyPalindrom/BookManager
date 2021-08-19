package com.example.bookmanager

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.room.Room

class RecyclerFragmentViewModel : ViewModel() {
    lateinit var bookAdapter: BookAdapter
    var bookDao: BookDao? = null
    lateinit var touchHelper: ItemTouchHelper
    private lateinit var database: AppDatabase

    fun initBookAdapter(listener: BookListener) {
        bookAdapter = BookAdapter(listener)
        touchHelper = ItemTouchHelper(
            SimpleItemTouchHelperCallback(
                bookAdapter
            )
        )
    }

    fun chooseSort(context: Context, resources: Resources) {
        bookAdapter.setBooks(bookDao?.getAll() as List<Book>)
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val option = prefs.getString("list_preference", "не установлено")
        if (option == resources.getString(R.string.first_option)) {
            bookAdapter.sortByTitle()
        }
        if (option == resources.getString(R.string.second_option)) {
            bookAdapter.sortByAuthor()
        }
        if (option == resources.getString(R.string.third_option)) {
            bookAdapter.sortByYear()
        }
    }

    fun updateDatabase() {
        if (bookDao?.getAll()?.size == 0) {
            bookAdapter.getBooks()?.forEach { bookDao?.insert(it) }
        } else {
            bookDao?.getAll()?.forEach {
                if (it != null) {
                    if (bookAdapter.getBooks()?.let { it1 -> checkList(it1, it.id) } == false) {
                        bookDao?.delete(it)
                    }
                }
            }
            bookAdapter.getBooks()?.forEach { stopwatch ->
                if (checkList(
                        bookDao?.getAll() as List<Book>,
                        stopwatch.id
                    )
                ) bookDao?.update(stopwatch)
                else bookDao?.insert(stopwatch)
            }

        }
    }

    private fun checkList(list: List<Book>, id: Int): Boolean {
        var temp = false
        list.forEach { if (it.id == id) temp = true }
        return temp
    }

    fun loadListFromDatabase(context: Context) {
        database =
            context?.let {
                Room.databaseBuilder(it, AppDatabase::class.java, "stopwatcheswithnames")
                    .allowMainThreadQueries()
                    .build()
            }
        bookDao = database.stopwatchDao()
        if (bookDao?.getAll()?.size != 0) {
            bookAdapter.setBooks(bookDao?.getAll() as List<Book>)
        }
    }

    fun deleteItem(position: Int) {
        bookDao?.delete(bookAdapter.getBooks()?.get(position))
    }
}