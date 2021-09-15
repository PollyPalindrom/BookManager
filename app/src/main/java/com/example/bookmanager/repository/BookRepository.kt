package com.example.bookmanager.repository

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.bookmanager.database.Book
import com.example.bookmanager.database.BookDao
import com.example.bookmanager.database.BookSQLiteOpenHelper
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val bookDao: BookDao,
    private val cursorDatabase: BookSQLiteOpenHelper
) {

    var state = true
    val books: Flow<MutableList<Book>> = bookDao.getAll()

    fun insert(book: Book) {
        if (state) bookDao.insert(book)
        else insertBook(book)
    }

    fun delete(book: Book) {
        if (state) bookDao.delete(book)
        else deleteBook(book)
    }

    fun update(book: Book) {
        if (state) bookDao.update(book)
        else updateBook(book)
    }

    fun getBook(id: Int): Book {
        return if (state) bookDao.getBook(id)
        else getBookCursor(id)
    }

    private fun insertBook(book: Book) {
        cursorDatabase.insertBook(book)
    }

    private fun updateBook(book: Book) {
        cursorDatabase.updateBook(book)
    }

    private fun deleteBook(book: Book) {
        cursorDatabase.deleteBook(book)
    }

    private fun getBookCursor(id: Int): Book {
        return cursorDatabase.getBook(id)
    }

    fun getAll(): List<Book> {
        return cursorDatabase.getAll()
    }
}