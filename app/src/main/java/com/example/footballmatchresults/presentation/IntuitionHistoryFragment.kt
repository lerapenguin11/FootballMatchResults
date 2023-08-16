package com.example.footballmatchresults.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.api.FootballApi.Companion.retrofitService
import com.example.footballmatchresults.business.models.slide.PointModel
import com.example.footballmatchresults.business.repos.LeagueProfileRepository
import com.example.footballmatchresults.databinding.FragmentIntuitionBinding
import com.example.footballmatchresults.presentation.adapter.PointIntuitionAdapter
import com.example.footballmatchresults.utilits.replaceFragment
import com.example.footballmatchresults.viewModel.LeagueResultViewModelFactory
import com.example.footballmatchresults.viewModel.LeagueResultsViewModel

class IntuitionHistoryFragment(val pointList : MutableList<PointModel>,
                               val id : String) : Fragment() {
    private var _binding : FragmentIntuitionBinding? = null
    private val binding get() = _binding!!

    private val adapterPoint  = PointIntuitionAdapter()
    private lateinit var viewModel : LeagueResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIntuitionBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, LeagueResultViewModelFactory(LeagueProfileRepository(
                retrofitService!!
            ))).get(
                LeagueResultsViewModel::class.java
            )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addText()
        onClick()
    }

    private fun onClick() {
        binding.btArrow.setOnClickListener {

        }

        binding.btInfoMatch.setOnClickListener {
            Toast.makeText(context, R.string.info_point, Toast.LENGTH_SHORT).show()
        }
    }

    fun addText() {
        binding.rvIntuitionHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvIntuitionHistory.adapter = adapterPoint
        adapterPoint.setItem(pointList)
        println(pointList)
    }
}