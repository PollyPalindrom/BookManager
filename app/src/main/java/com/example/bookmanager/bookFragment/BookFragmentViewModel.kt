package com.example.bookmanager.bookFragment

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.bookmanager.MainActivity
import com.example.bookmanager.database.AppDatabase
import com.example.bookmanager.database.Book
import com.example.bookmanager.database.BookDao

class BookFragmentViewModel : ViewModel() {

    private var bookDao: BookDao? = null
    private lateinit var database: AppDatabase

    fun addButtonHandler(
        context: Context,
        activity: MainActivity,
        title: String,
        author: String,
        year: String
    ) {
        if (title != "" && author != "" && year != "") {
            database =
                context.let {
                    Room.databaseBuilder(it, AppDatabase::class.java, "stopwatcheswithnames")
                        .allowMainThreadQueries()
                        .build()
                }
            bookDao = database.stopwatchDao()
            bookDao?.insert(
                Book(
                    title,
                    author,
                    year
                )
            )
            activity.supportFragmentManager.popBackStack()
        } else Toast.makeText(
            activity.applicationContext,
            "Wrong input :3",
            Toast.LENGTH_LONG
        ).show()
    }
}