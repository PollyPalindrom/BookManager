package com.example.bookmanager

import com.example.bookmanager.database.Book

interface BookListener {
    fun deleteItem(book: Book)
}