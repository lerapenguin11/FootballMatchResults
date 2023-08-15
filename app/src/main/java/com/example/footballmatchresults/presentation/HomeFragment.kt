package com.example.footballmatchresults.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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
import com.example.footballmatchresults.business.models.slide.NewsModel
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
    private val adapterSlider = NewsSliderAdapter(this)

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

        binding.navRules.setOnClickListener{

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

    @SuppressLint("CutPasteId")
    override fun newsList(data: NewsModel) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.full_screen_dialog_news)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val title : TextView = dialog.findViewById(R.id.tv_title_dialog_news)
        val date : TextView = dialog.findViewById(R.id.tv_title_dialog_news)
        val text : TextView = dialog.findViewById(R.id.tv_text_dialog_news)
        val btArrow : ImageView = dialog.findViewById(R.id.bt_close_dialog_news)

        title.text = data.title
        date.text = data.date
        text.text = data.text

        dialog.show()

        btArrow.setOnClickListener {
            dialog.cancel()
        }
    }
}