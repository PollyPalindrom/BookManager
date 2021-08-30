package com.example.bookmanager.sortFragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.bookmanager.MainActivity
import com.example.bookmanager.R

class SortFragment : PreferenceFragmentCompat() {

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
    }

}