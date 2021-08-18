package com.example.bookmanager

import androidx.room.*

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book?>?

    @Insert(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(stopwatches: Book?)

    @Update
    fun update(stopwatches: Book?)

    @Delete
    fun delete(stopwatches: Book?)
}