package com.example.footballmatchresults.presentation

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
import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.repos.LeagueProfileRepository
import com.example.footballmatchresults.databinding.FragmentLeagueResultsBinding
import com.example.footballmatchresults.presentation.adapter.LeagueProfileAdapter
import com.example.footballmatchresults.viewModel.LeagueResultsViewModel
import com.example.footballmatchresults.viewModel.LeagueResultViewModelFactory

class LeagueResultsFragment(val id : String) : Fragment() {

    private var _binding : FragmentLeagueResultsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : LeagueResultsViewModel
    private val retrofitService = FootballApi.getInstance()
    private val adapter = LeagueProfileAdapter()

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
}