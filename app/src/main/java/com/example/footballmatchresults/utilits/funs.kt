package com.example.footballmatchresults.utilits

import androidx.fragment.app.Fragment
import com.example.footballmatchresults.APP_MAIN_ACTIVITY
import com.example.footballmatchresults.R

fun replaceFragmentMainActivity(fragment: Fragment, addStack: Boolean = true) {
    if (addStack) {
        APP_MAIN_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.main_layout,
                fragment
            ).commit()
    } else {
        APP_MAIN_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_layout,
                fragment
            ).commit()
    }
}