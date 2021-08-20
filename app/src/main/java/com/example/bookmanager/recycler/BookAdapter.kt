package com.example.bookmanager.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanager.BookListener
import com.example.bookmanager.itemTouchHelper.ItemTouchHelperAdapter
import com.example.bookmanager.database.Book
import com.example.bookmanager.databinding.ItemBinding
import java.util.*

class BookAdapter(private val listener: BookListener) : RecyclerView.Adapter<BookViewHolder>(),
    ItemTouchHelperAdapter {

    private var books = MutableLiveData<MutableList<Book>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun getItem(position: Int): Book? {
        return books.value?.get(position)
    }

    override fun getItemCount(): Int {
        return books.value?.size!!
    }

    fun sortByTitle() {
        books.value?.sortedBy { it.title }
    }

    fun sortByAuthor() {
        books.value?.sortedBy { it.author }
    }

    fun sortByYear() {
        books.value?.sortedBy { it.year }
    }

    fun setBooks(stopwatchList: List<Book>) {
        books.value = stopwatchList as MutableList<Book>
    }

    fun addBook(stopwatch: Book) {
        books.value?.add(stopwatch)
    }

    fun deleteBook(position: Int) {
        if (position >= 0) {
            books.value?.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getBooks(): MutableList<Book>? {
        return books.value
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(books.value, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(books.value, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        deleteBook(position)
    }
}