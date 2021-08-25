package com.example.bookmanager.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.bookmanager.database.Book

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean{
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.author == newItem.author && oldItem.title == newItem.title && oldItem.year == newItem.year && oldItem.id == newItem.id
    }
}