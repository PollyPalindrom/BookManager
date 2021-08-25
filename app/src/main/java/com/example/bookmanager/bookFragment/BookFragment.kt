package com.example.bookmanager.bookFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookmanager.BooksApplication
import com.example.bookmanager.MainActivity
import com.example.bookmanager.viewModelFactory.ViewModelFactory
import com.example.bookmanager.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private var binding: FragmentBookBinding? = null
    private val viewModel: BookFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as BooksApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        binding?.addButton?.setOnClickListener {

            viewModel.addButtonHandler(
                activity as MainActivity,
                binding?.title?.text.toString(),
                binding?.author?.text.toString(),
                binding?.year?.text.toString()
            )

        }
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mainActivity.supportFragmentManager.popBackStack()
                }
            })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}