package com.example.bookmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanager.databinding.ItemBinding
import java.util.*

class BookAdapter(private val listener: BookListener) : RecyclerView.Adapter<BookViewHolder>(),
    ItemTouchHelperAdapter {

    private var books = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Book {
        return books[position]
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun sortByTitle() {
        books.sortBy { it.title }
        notifyDataSetChanged()
    }

    fun sortByAuthor() {
        books.sortBy { it.author }
    }

    fun sortByYear() {
        books.sortBy { it.year }
    }

    fun setBooks(stopwatchList: List<Book>) {
        books = stopwatchList as MutableList<Book>
    }

    fun addBook(stopwatch: Book) {
        books.add(stopwatch)
        notifyItemInserted(books.size - 1)
    }

    fun deleteBook(position: Int) {
        if (position >= 0) {
            books.removeAt(position)
            listener.deleteItem(position)
            notifyItemRemoved(position)
        }
    }

    fun getBooks(): MutableList<Book> {
        return books
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(books, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(books, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        deleteBook(position)
    }
}