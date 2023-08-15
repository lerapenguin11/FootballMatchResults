package com.example.footballmatchresults.business.repos

import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.api.FootballApi.Companion.retrofitService

class LeagueRepository(private val api: FootballApi) {

    fun getReloadDataLeague() = retrofitService!!.getLeague()
}