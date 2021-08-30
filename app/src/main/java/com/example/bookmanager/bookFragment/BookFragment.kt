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
import com.example.bookmanager.databinding.FragmentBookBinding
import com.example.bookmanager.viewModelFactory.ViewModelFactory

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
        binding?.addButton?.text = arguments?.getString(COMMAND).toString()
        if(binding?.addButton?.text == "edit"){
            arguments?.getInt(BOOK_ID)?.let { viewModel.getInfo(it, binding!!) }
        }
        binding?.addButton?.setOnClickListener {
            viewModel.addButtonHandler(
                activity as MainActivity,
                binding?.title?.text.toString(),
                binding?.author?.text.toString(),
                binding?.year?.text.toString(),
                binding?.addButton?.text.toString(),
                arguments?.getInt(BOOK_ID)
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

    companion object{
        @JvmStatic
        fun newInstance(command: String, id:Int): BookFragment {
            val fragment = BookFragment()
            val args = Bundle()
            args.putString(COMMAND, command)
            args.putInt(BOOK_ID,id)
            fragment.arguments = args
            return fragment

        }

        private const val COMMAND = "COMMAND"
        private const val BOOK_ID = "BOOK_ID"
    }
}