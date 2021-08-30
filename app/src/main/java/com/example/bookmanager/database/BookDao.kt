package com.example.bookmanager.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): Flow<MutableList<Book>>

    @Insert(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(stopwatches: Book?)

    @Update
    fun update(stopwatches: Book?)

    @Delete
    fun delete(stopwatches: Book?)

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBook(id:Int):Book
}