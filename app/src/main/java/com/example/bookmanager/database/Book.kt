package com.example.bookmanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var title: String, var author: String, var year: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}