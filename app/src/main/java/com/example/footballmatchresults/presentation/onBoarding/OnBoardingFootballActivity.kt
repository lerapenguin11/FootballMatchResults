package com.example.footballmatchresults.presentation.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.footballmatchresults.R
import com.example.footballmatchresults.presentation.MainActivity
import com.example.footballmatchresults.presentation.adapter.onBoarding.OnBoardingFootballAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingFootballActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_football)
        setViewPager()
    }

    private fun setViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ThirstFragment())
        fragmentList.add(SecondFragment())

        val adapterViewPager = OnBoardingFootballAdapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )
        val finish = findViewById<ConstraintLayout>(R.id.finish)

        finish.setOnClickListener { v: View? ->
            val intent = Intent(
                this@OnBoardingFootballActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapterViewPager
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)
    }
}