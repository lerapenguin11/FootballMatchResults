package com.example.footballmatchresults.presentation.adapter.onBoarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingFootballAdapter(arrayList : ArrayList<Fragment>, fragmentManager : FragmentManager, lc : Lifecycle)
    : FragmentStateAdapter(fragmentManager, lc) {

    private val fragmentListOnBoarding = arrayList

    override fun getItemCount(): Int = fragmentListOnBoarding.size

    override fun createFragment(position: Int): Fragment = fragmentListOnBoarding[position]
}
