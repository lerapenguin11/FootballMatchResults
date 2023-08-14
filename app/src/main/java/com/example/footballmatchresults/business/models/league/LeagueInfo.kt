package com.example.footballmatchresults.business.models.league

data class LeagueInfo(
    val color: String,
    val currentRound: Int,
    val currentSeason: String,
    val leagueId: String,
    val logo: String,
    val name: String,
    val shortName: String,
    val totalRound: Int
)