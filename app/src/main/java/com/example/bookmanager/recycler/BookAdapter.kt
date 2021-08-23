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

class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {

    private var books = listOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun getItem(position: Int): Book? {
        return books[position]
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setBooks(stopwatchList: List<Book>) {
        books = stopwatchList
        notifyDataSetChanged()
    }

}