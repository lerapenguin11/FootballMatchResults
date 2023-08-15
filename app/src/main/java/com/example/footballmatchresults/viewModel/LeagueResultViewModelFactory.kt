package com.example.footballmatchresults.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballmatchresults.business.repos.LeagueProfileRepository

class LeagueResultViewModelFactory constructor(private val repository: LeagueProfileRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {

        //super.create(modelClass)
        return return if (modelClass.isAssignableFrom(LeagueResultsViewModel::class.java)) {
            LeagueResultsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}