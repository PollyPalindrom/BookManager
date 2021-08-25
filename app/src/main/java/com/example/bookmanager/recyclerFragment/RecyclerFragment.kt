package com.example.bookmanager.recyclerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmanager.BooksApplication
import com.example.bookmanager.MainActivity
import com.example.bookmanager.R
import com.example.bookmanager.viewModelFactory.ViewModelFactory
import com.example.bookmanager.databinding.FragmentRecyclerBinding
import com.example.bookmanager.itemTouchHelper.SimpleItemTouchHelperCallback
import com.example.bookmanager.recycler.BookAdapter

class RecyclerFragment : Fragment() {

    private val viewModel:RecyclerFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as BooksApplication).repository)
    }
    private var binding: FragmentRecyclerBinding? = null
    val bookAdapter = BookAdapter()
    lateinit var touchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        touchHelper = ItemTouchHelper(
            SimpleItemTouchHelperCallback(
                viewModel
            )
        )
        viewModel.books.observe(viewLifecycleOwner) {
            bookAdapter.submitList(it)
        }
        val mainActivity = activity as MainActivity
        binding?.recycler?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
        binding?.addNewStopwatchButton?.setOnClickListener {
            mainActivity.openBookFragment()
        }
        touchHelper.attachToRecyclerView(binding?.recycler)
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mainActivity.finishAffinity()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        viewModel.chooseSort(requireContext(),resources)
        val mainActivity = activity as MainActivity
        mainActivity.getBinding()?.toolbar?.navigationIcon =
            resources.getDrawable(R.drawable.ic_baseline_list_24)
        mainActivity.getBinding()?.toolbar?.setOnClickListener {
            mainActivity.openSortFragment()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}