package com.example.bookmanager.repository

import com.example.bookmanager.database.Book
import com.example.bookmanager.database.BookDao
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    val books: Flow<MutableList<Book>> = bookDao.getAll()

    fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun delete(book: Book) {
        bookDao.delete(book)
    }
}