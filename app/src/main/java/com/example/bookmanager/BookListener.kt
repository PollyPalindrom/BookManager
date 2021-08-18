package com.example.bookmanager

interface BookListener {
    fun loadListFromDatabase()
    fun deleteItem(position: Int)
}