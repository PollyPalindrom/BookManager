package com.example.bookmanager.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bookmanager.database.Book


private const val DATABASE_NAME = "books"
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(50), author VARCHAR(50), year VARCHAR(50));"

class BookSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + "book")
        onCreate(db)
    }

    fun getAll(): List<Book> {
        val books = mutableListOf<Book>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM book", null)
        if (cursor.moveToFirst()) {
            do {
                val book = Book("", "", 0)
                book.id = cursor.getInt(cursor.getColumnIndex("id"))
                book.title = cursor.getString(cursor.getColumnIndex("title"))
                book.author = cursor.getString(cursor.getColumnIndex("author"))
                book.year = cursor.getLong(cursor.getColumnIndex("year"))
                books.add(book)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return books
    }

    fun getBook(id: Int): Book {
        val book = Book("", "", 0)
        val cursor = readableDatabase.rawQuery("SELECT * FROM book WHERE id = :id", null)
        if (cursor.moveToFirst()) {
            book.id = cursor.getInt(cursor.getColumnIndex("id"))
            book.title = cursor.getString(cursor.getColumnIndex("title"))
            book.author = cursor.getString(cursor.getColumnIndex("author"))
            book.year = cursor.getLong(cursor.getColumnIndex("year"))
        }
        cursor.close()
        return book
    }

    fun deleteBook(book: Book) {
        readableDatabase.delete("book", "id" + "=" + book.id, null)

    }

    fun updateBook(book: Book) {
        val args = ContentValues()
        args.put("title", book.title)
        args.put("author", book.author)
        args.put("year", book.year)
        readableDatabase.update("book", args, "id" + "=" + book.id, null)
    }

    fun insertBook(book: Book): Long {
        val args = ContentValues()
        args.put("title", book.title)
        args.put("author", book.author)
        args.put("year", book.year)
        return readableDatabase.insert("book", null, args)
    }

}