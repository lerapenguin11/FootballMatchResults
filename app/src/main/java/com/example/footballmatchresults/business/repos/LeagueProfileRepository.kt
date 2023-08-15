package com.example.footballmatchresults.business.repos

import com.example.footballmatchresults.business.api.FootballApi

class LeagueProfileRepository(private val api: FootballApi) {

    fun getReloadDataLeagueProfile(id : String) = FootballApi.retrofitService!!.getLeagueResult(leagueId = id)
}