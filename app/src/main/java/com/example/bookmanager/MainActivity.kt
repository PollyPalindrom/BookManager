package com.example.bookmanager

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmanager.bookFragment.BookFragment
import com.example.bookmanager.databinding.ActivityMainBinding
import com.example.bookmanager.recyclerFragment.RecyclerFragment
import com.example.bookmanager.sortFragment.SortFragment


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (savedInstanceState == null) openRecyclerFragment()
    }

    private fun openRecyclerFragment() {
        val recyclerFragment = RecyclerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, recyclerFragment).commit()
        binding?.toolbar?.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_list_24)
        binding?.toolbar?.setOnClickListener {
            openSortFragment()
        }
    }

    fun openSortFragment() {
        val sortFragment = SortFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, sortFragment).commit()
        transaction.addToBackStack("fragment")
        binding?.toolbar?.navigationIcon =
            resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        binding?.toolbar?.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    fun openBookFragment() {
        val bookFragment = BookFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, bookFragment).commit()
        transaction.addToBackStack("fragment")
        binding?.toolbar?.navigationIcon =
            resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        binding?.toolbar?.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    fun getMyApplication(): Application? {
        return application
    }

    fun getBinding(): ActivityMainBinding? {
        return binding
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}