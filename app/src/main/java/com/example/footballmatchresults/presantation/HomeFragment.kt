package com.example.footballmatchresults.presantation

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
import com.example.footballmatchresults.business.models.league.LeagueModel
import com.example.footballmatchresults.business.repos.LeagueRepository
import com.example.footballmatchresults.databinding.FragmentHomeBinding
import com.example.footballmatchresults.presantation.adapter.LeagueAdapter
import com.example.footballmatchresults.viewModel.HomeViewModel
import com.example.footballmatchresults.viewModel.MyViewModelFactory

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: HomeViewModel
    private val retrofitService = FootballApi.getInstance()
    val adapter = LeagueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setAdapterLeague()
    }

    private fun setAdapterLeague() {
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(LeagueRepository(retrofitService))).get(
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
}