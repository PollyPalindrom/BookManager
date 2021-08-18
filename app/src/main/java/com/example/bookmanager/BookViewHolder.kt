package com.example.bookmanager

import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanager.databinding.ItemBinding

class BookViewHolder(
    private val binding: ItemBinding
) :
    RecyclerView.ViewHolder(binding.root), LifecycleObserver {
    fun bind(book: Book) {
        binding.bookTitle.text = book.title
        binding.bookAuthor.text = book.author
        binding.editYear.text = book.year
    }

}