package com.example.bookmanager.bookFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookmanager.MainActivity
import com.example.bookmanager.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private lateinit var binding: FragmentBookBinding
    private lateinit var viewModel: BookFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(BookFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        binding.addButton.setOnClickListener {
            context?.let { it1 ->
                viewModel.addButtonHandler(
                    it1,
                    activity as MainActivity,
                    binding.title.text.toString(),
                    binding.author.text.toString(),
                    binding.year.text.toString()
                )
            }
        }
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mainActivity.supportFragmentManager.popBackStack()
                }
            })
    }

}