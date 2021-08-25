package com.example.bookmanager.bookFragment

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmanager.repository.BookRepository
import com.example.bookmanager.MainActivity
import com.example.bookmanager.database.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookFragmentViewModel(private val repository: BookRepository) : ViewModel() {

    fun addButtonHandler(
        activity: MainActivity,
        title: String?,
        author: String?,
        year: String?
    ) {
        if (title != "" && author != "" && year != "" && title != null && author != null && year != null) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.insert(
                    Book(
                        title,
                        author,
                        year
                    )
                )
            }
            activity.supportFragmentManager.popBackStack()
        } else Toast.makeText(
            activity.applicationContext,
            "Wrong input :3",
            Toast.LENGTH_LONG
        ).show()
    }
}