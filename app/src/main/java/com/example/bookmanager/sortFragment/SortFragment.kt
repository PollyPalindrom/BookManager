package com.example.bookmanager.sortFragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.bookmanager.BooksApplication
import com.example.bookmanager.MainActivity
import com.example.bookmanager.R
import com.example.bookmanager.viewModelFactory.ViewModelFactory


class SortFragment : PreferenceFragmentCompat() {

    private val viewModel: SortFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as BooksApplication).repository)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.filter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainActivity.onBackPressedDispatcher.addCallback(
            mainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mainActivity.supportFragmentManager.popBackStack()
                }
            })
        val switch: SwitchPreference? =
            findPreference("room_or_cursor") as SwitchPreference?
        switch?.setOnPreferenceChangeListener { preference, newValue ->
            viewModel.changeState(newValue as Boolean)
            if (newValue as Boolean) (activity as MainActivity).changeSubtitle("Room")
            else (activity as MainActivity).changeSubtitle("Cursor")
            true
        }
    }

}