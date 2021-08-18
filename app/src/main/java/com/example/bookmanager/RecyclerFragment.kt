package com.example.bookmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmanager.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment(), BookListener {

    private lateinit var viewModel: RecyclerFragmentViewModel
    private lateinit var binding: FragmentRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(RecyclerFragmentViewModel::class.java)
        viewModel.initBookAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        loadListFromDatabase()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModel.bookAdapter
        }
        binding.addNewStopwatchButton.setOnClickListener {
            mainActivity.openBookFragment()
        }
        binding.toolbar.setOnClickListener {
            mainActivity.openSortFragment()
        }
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    ActivityCompat.finishAffinity(activity as MainActivity)
                }
            })
        viewModel.touchHelper.attachToRecyclerView(binding.recycler)
    }

    override fun onResume() {
        super.onResume()
        context?.let { viewModel.chooseSort(it, resources) }
    }

    override fun onStop() {
        super.onStop()
        viewModel.updateDatabase()
    }

    override fun loadListFromDatabase() {
        context?.let { viewModel.loadListFromDatabase(it) }
    }

    override fun deleteItem(position: Int) {
        viewModel.deleteItem(position)
    }
}