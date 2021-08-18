package com.example.bookmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmanager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openRecyclerFragment()
    }

    private fun openRecyclerFragment() {
        val recyclerFragment = RecyclerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, recyclerFragment).commit()
    }

    fun openSortFragment() {
        val sortFragment = SortFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, sortFragment).commit()
        transaction.addToBackStack("fragment")
    }

    fun openBookFragment() {
        val bookFragment = BookFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, bookFragment).commit()
        transaction.addToBackStack("fragment")
    }
}