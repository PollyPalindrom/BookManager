package com.example.bookmanager

import android.app.Application
import com.example.bookmanager.database.AppDatabase
import com.example.bookmanager.database.BookSQLiteOpenHelper
import com.example.bookmanager.repository.BookRepository

class BooksApplication : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val cursorDatabase by lazy { BookSQLiteOpenHelper(this) }
    val repository by lazy { BookRepository(database.stopwatchDao(), cursorDatabase) }
}