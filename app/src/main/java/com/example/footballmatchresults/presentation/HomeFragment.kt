package com.example.footballmatchresults.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.models.league.Data
import com.example.footballmatchresults.business.repos.LeagueRepository
import com.example.footballmatchresults.databinding.FragmentHomeBinding
import com.example.footballmatchresults.presentation.adapter.LeagueAdapter
import com.example.footballmatchresults.presentation.adapter.slider.NewsSliderAdapter
import com.example.footballmatchresults.presentation.adapter.listener.LeagueListener
import com.example.footballmatchresults.utilits.replaceFragment
import com.example.footballmatchresults.viewModel.HomeViewModel
import com.example.footballmatchresults.viewModel.HomeViewModelFactory
import kotlin.math.abs

class HomeFragment : Fragment(), LeagueListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: HomeViewModel
    private val retrofitService = FootballApi.getInstance()
    val adapter = LeagueAdapter(this)
    private lateinit var viewPager: ViewPager2
    private val adapterSlider = NewsSliderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setViewPager()
        setAdapterLeague()
        onClick()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setViewPager() {
        viewModel =
            ViewModelProvider(this, HomeViewModelFactory(LeagueRepository(retrofitService))).get(
                HomeViewModel::class.java
            )

        viewModel.getNewsSlider().observe(requireActivity()){
            adapterSlider.setItem(it)
        }
        viewPager = view!!.findViewById(R.id.home_view_pager)
        viewPager.adapter = adapterSlider
        viewPager.clipChildren = false
        viewPager.clipToPadding = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer{
            override fun transformPage(page: View, position: Float) {
                val r = 1 - abs(position)

                page.scaleY = 0.05f + r + 0.15f
            }
        })

        viewPager.setPageTransformer(compositePageTransformer)
    }

    private fun onClick() {
        binding.btInfo.setOnClickListener {
            Toast.makeText(context, R.string.home_info, Toast.LENGTH_LONG).show()
        }
    }

    private fun setAdapterLeague() {
        viewModel =
            ViewModelProvider(this, HomeViewModelFactory(LeagueRepository(retrofitService))).get(
                HomeViewModel::class.java
            )
        binding.listLeague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listLeague.adapter = adapter

        viewModel.getLeague()

        viewModel.leagueList.observe(this, Observer {
            if (it != null) {
                Log.d("HOME_FRAGMENT", "movieList: $it")
                adapter.setItem(it)
            } else {
                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d("HOME_FRAGMENT", "errorMessage: $it")
        })
    }

    override fun onDestroy() {
        viewModel.disposable.dispose()
        super.onDestroy()
    }

    override fun leagueList(list: Data) {
        replaceFragment(LeagueResultsFragment(list.leagueId))
    }
}