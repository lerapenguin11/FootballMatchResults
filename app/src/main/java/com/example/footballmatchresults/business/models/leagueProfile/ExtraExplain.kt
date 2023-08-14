package com.example.footballmatchresults.business.models.leagueProfile

data class ExtraExplain(
    val awayScore: Int,
    val extraAwayScore: Int,
    val extraHomeScore: Int,
    val extraTimeStatus: Int,
    val homeScore: Int,
    val kickOff: Int,
    val minute: Int,
    val penAwayScore: Int,
    val penHomeScore: Int,
    val twoRoundsAwayScore: Int,
    val twoRoundsHomeScore: Int,
    val winner: Int
)