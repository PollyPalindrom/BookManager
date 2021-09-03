package com.example.bookmanager.bookFragment

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmanager.MainActivity
import com.example.bookmanager.database.Book
import com.example.bookmanager.databinding.FragmentBookBinding
import com.example.bookmanager.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookFragmentViewModel(private val repository: BookRepository) : ViewModel() {

    fun getInfo(id:Int, binding: FragmentBookBinding) {
        viewModelScope.launch(Dispatchers.IO) {
            val book = id.let { repository.getBook(it) }
            binding.title.setText(book.title)
            binding.author.setText(book.author)
            binding.year.setText(book.year.toString())
        }
    }

    fun addButtonHandler(
        activity: MainActivity,
        title: String?,
        author: String?,
        year: String?,
        command: String?,
        id: Int?
    ) {
        if (title != "" && author != "" && year != "" && title != null && author != null && year?.let {
                checkNumber(
                    it
                )
            } == true) {
            if (command == "add")
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insert(
                        Book(
                            title,
                            author,
                            year.toLong()
                        )
                    )
                }
            else {
                if (id != 0)
                    viewModelScope.launch(Dispatchers.IO) {
                        val book = id?.let { repository.getBook(it) }
                        book?.year = year.toLong()
                        book?.author = author
                        book?.title = title
                        if (book != null) {
                            repository.update(
                                book
                            )
                        }
                    }
            }
            activity.supportFragmentManager.popBackStack()
        } else Toast.makeText(
            activity.applicationContext,
            "Wrong input :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun checkNumber(numberToCompare: String): Boolean {
        var sum: Long = 0
        try {
            numberToCompare.toLong()
        } catch (e: NumberFormatException) {
            return false
        }
        if (numberToCompare == "") return false
        if (numberToCompare.toLong() <= Integer.MAX_VALUE.toLong()) return true
        if (numberToCompare.toLong() > Integer.MAX_VALUE) return false
        else {
            for (i in numberToCompare.indices) {
                sum = sum * 10 + numberToCompare[i].toInt()
                if (sum > Integer.MAX_VALUE) return false
            }
            return true
        }
    }
}