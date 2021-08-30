package com.example.bookmanager.recycler

import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanager.Listener
import com.example.bookmanager.database.Book
import com.example.bookmanager.databinding.ItemBinding

class BookViewHolder(
    private val binding: ItemBinding
) :
    RecyclerView.ViewHolder(binding.root), LifecycleObserver {
    fun bind(book: Book, listener: Listener) {
        binding.bookTitle.text = book.title
        binding.bookAuthor.text = book.author
        binding.editYear.text = book.year
        binding.editText.setOnClickListener {
            listener.openBookFragmentForEdit(book.id)
        }
    }
}