package com.example.footballmatchresults.presentation.adapter.listener

import com.example.footballmatchresults.business.models.leagueProfile.Data

interface LeagueInformationListener {

    fun leagueInfo(data : Data, logo : com.example.footballmatchresults.business.models.leagueProfileLogo.Data)
}