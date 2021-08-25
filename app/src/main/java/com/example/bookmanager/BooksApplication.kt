package com.example.bookmanager

import android.app.Application
import com.example.bookmanager.database.AppDatabase
import com.example.bookmanager.repository.BookRepository

class BooksApplication:Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { BookRepository(database.stopwatchDao()) }
}