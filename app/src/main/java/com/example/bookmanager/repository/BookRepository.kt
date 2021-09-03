package com.example.bookmanager.repository

import com.example.bookmanager.database.Book
import com.example.bookmanager.database.BookDao
import com.example.bookmanager.database.BookSQLiteOpenHelper
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val bookDao: BookDao,
    private val cursorDatabase: BookSQLiteOpenHelper
) {

    val books: Flow<MutableList<Book>> = bookDao.getAll()

    fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun delete(book: Book) {
        bookDao.delete(book)
    }

    fun update(book: Book) {
        bookDao.update(book)
    }

    fun getBook(id: Int): Book {
        return bookDao.getBook(id)
    }

    fun insertBook(book: Book) {
        cursorDatabase.insertBook(book)
    }

    fun updateBook(book: Book) {
        cursorDatabase.updateBook(book)
    }

    fun deleteBook(book: Book) {
        cursorDatabase.deleteBook(book)
    }

    fun getBook(book: Book):Book {
        return cursorDatabase.getBook(book.id)
    }

    fun getAll():List<Book>{
        return cursorDatabase.getAll()
    }
}