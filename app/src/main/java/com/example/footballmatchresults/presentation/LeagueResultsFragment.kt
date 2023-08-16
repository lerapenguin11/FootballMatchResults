package com.example.footballmatchresults.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
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
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.models.leagueProfile.Data
import com.example.footballmatchresults.business.repos.LeagueProfileRepository
import com.example.footballmatchresults.presentation.adapter.LeagueProfileAdapter
import com.example.footballmatchresults.presentation.adapter.listener.LeagueInformationListener
import com.example.footballmatchresults.viewModel.LeagueResultsViewModel
import com.example.footballmatchresults.viewModel.LeagueResultViewModelFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.footballmatchresults.PREFS_KEY
import com.example.footballmatchresults.business.models.slide.PointModel
import com.example.footballmatchresults.business.models.slide.SoonMatchModel
import com.example.footballmatchresults.databinding.FragmentLeagueResultsBinding
import com.example.footballmatchresults.presentation.adapter.PointIntuitionAdapter
import com.example.footballmatchresults.presentation.adapter.listener.SoonMatchListener
import com.example.footballmatchresults.presentation.adapter.slider.SoonMatchAdapter
import com.example.footballmatchresults.utilits.replaceFragment
import com.example.footballmatchresults.viewModel.PointViewModel
import com.google.gson.Gson
import kotlin.math.abs
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LeagueResultsFragment(val id : String) : Fragment(),
    LeagueInformationListener,
    SoonMatchListener {

    private var _binding : FragmentLeagueResultsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : LeagueResultsViewModel
    private val retrofitService = FootballApi.getInstance()
    private val adapter = LeagueProfileAdapter(this)
    private val adapterSlider = SoonMatchAdapter(this)
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueResultsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setAdapterLeagueProfile()
        setViewPager()
        onClick()
    }

    private fun onClick() {
        binding.btInfoMatch.setOnClickListener {
            Toast.makeText(context, R.string.league_result_info, Toast.LENGTH_LONG).show()
        }

        binding.btArrow.setOnClickListener { replaceFragment(HomeFragment()) }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setViewPager() {
        viewModel =
            ViewModelProvider(this, LeagueResultViewModelFactory(LeagueProfileRepository(retrofitService))).get(
                LeagueResultsViewModel::class.java
            )

        viewModel.getSoonMatchSlider().observe(requireActivity()){
            adapterSlider.setItem(it)
        }

        viewPager = view!!.findViewById(R.id.result_view_pager)
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

    private fun setAdapterLeagueProfile() {
        viewModel =
            ViewModelProvider(this, LeagueResultViewModelFactory(LeagueProfileRepository(retrofitService))).get(
                LeagueResultsViewModel::class.java
            )

        binding.rvLeagueResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvLeagueResult.adapter = adapter

        viewModel.getLeagueResult(id = id)

        viewModel.leagueList.observe(this, Observer {
            if (it != null) {
                Log.d("RESULT_FRAGMENT", "movieList: $it")
                adapter.setItem(it)
            } else {
                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d("HOME_FRAGMENT", "errorMessage: $it")
        })
    }

    override fun leagueInfo(data: Data) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.full_screen_dialog_league_result)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val resultHome : TextView = dialog.findViewById(R.id.tv_result_home)
        val resultAway : TextView = dialog.findViewById(R.id.tv_result_away)
        val nameHome : TextView = dialog.findViewById(R.id.tv_name_home)
        val nameAway : TextView = dialog.findViewById(R.id.tv_name_away)
        val homeHalfScore : TextView = dialog.findViewById(R.id.tv_home_half_score)
        val awayHalfScore : TextView = dialog.findViewById(R.id.tv_away_half_score)
        val homeRed : TextView = dialog.findViewById(R.id.tv_home_red)
        val awayRed : TextView = dialog.findViewById(R.id.tv_away_red)
        val homeYellow : TextView = dialog.findViewById(R.id.tv_home_yellow)
        val awayYellow : TextView = dialog.findViewById(R.id.tv_away_yellow)
        val homeCorner : TextView = dialog.findViewById(R.id.tv_home_corner)
        val awayCorner : TextView = dialog.findViewById(R.id.tv_away_corner)
        val homeRank : TextView = dialog.findViewById(R.id.tv_home_rank)
        val awayRank : TextView = dialog.findViewById(R.id.tv_away_rank)
        val round : TextView = dialog.findViewById(R.id.tv_round)
        val location : TextView = dialog.findViewById(R.id.tv_location)
        val weather : TextView = dialog.findViewById(R.id.tv_weather)
        val temperature : TextView = dialog.findViewById(R.id.tv_temperature)

        val btBack : ImageView = dialog.findViewById(R.id.bt_arrow_back)
        val info : ImageView = dialog.findViewById(R.id.bt_info_dialog)

        resultHome.text = data.homeScore.toString()
        resultAway.text = data.awayScore.toString()
        nameHome.text = data.homeName
        nameAway.text = data.awayName
        homeHalfScore.text = data.homeHalfScore.toString()
        awayHalfScore.text = data.awayHalfScore.toString()
        homeRed.text = data.homeRed.toString()
        awayRed.text = data.awayRed.toString()
        homeYellow.text = data.homeYellow.toString()
        awayYellow.text = data.awayYellow.toString()
        homeCorner.text = data.homeCorner.toString()
        awayCorner.text = data.awayCorner.toString()
        homeRank.text = data.homeRank
        awayRank.text = data.awayRank
        round.text = data.round
        location.text = data.location
        weather.text = data.weather
        temperature.text = data.temperature
        dialog.show()

        btBack.setOnClickListener { dialog.cancel() }

        info.setOnClickListener {
            Toast.makeText(context, R.string.league_result_dialog, Toast.LENGTH_LONG).show()
        }
    }

    override fun soonMatchList(list: SoonMatchModel) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.full_screen_dialog_enter_points)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val date : TextView = dialog.findViewById(R.id.tv_date_enter_point)
        val nameHome : TextView = dialog.findViewById(R.id.tv_name_home_point_enter)
        val nameAway : TextView = dialog.findViewById(R.id.tv_name_away_point_enter)
        val enterHomeResult : TextView = dialog.findViewById(R.id.et_enter_point_home)
        val enterAwayResult : TextView = dialog.findViewById(R.id.et_enter_point_away)
        val save : ConstraintLayout = dialog.findViewById(R.id.bt_save)
        val history : ConstraintLayout = dialog.findViewById(R.id.bt_history)

        dialog.show()

        date.text = list.date
        nameHome.text = list.nameHome
        nameAway.text = list.nameAway

        save.setOnClickListener {
            var text1 = enterHomeResult.text.toString()
            var text2 : String= enterAwayResult.text.toString()
            var pointHome = PointModel(text1, text2)
            viewModel.pointsList.add(pointHome)
            val list = viewModel.pointsList

            Toast.makeText(context, text1, Toast.LENGTH_SHORT).show()
            replaceFragment(IntuitionHistoryFragment(list, id))
            dialog.cancel()
        }


    }
}