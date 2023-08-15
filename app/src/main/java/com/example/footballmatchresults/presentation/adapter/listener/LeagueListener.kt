package com.example.footballmatchresults.presentation.adapter.listener

import com.example.footballmatchresults.business.models.league.Data
import com.example.footballmatchresults.business.models.slide.NewsModel

interface LeagueListener {

    fun leagueList(list : Data)

    fun newsList(data : NewsModel)
}