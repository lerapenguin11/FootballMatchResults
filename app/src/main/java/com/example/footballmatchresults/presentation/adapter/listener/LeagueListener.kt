package com.example.footballmatchresults.presentation.adapter.listener

import com.example.footballmatchresults.business.models.league.Data

interface LeagueListener {

    fun leagueList(list : Data)
}