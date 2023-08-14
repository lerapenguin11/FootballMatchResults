package com.example.footballmatchresults.business.models.league

data class Data(
    val areaId: Int,
    val color: String,
    val country: String,
    val countryId: String,
    val countryLogo: String,
    val currentRound: Int,
    val currentSeason: String,
    val leagueId: String,
    val logo: String,
    val name: String,
    val shortName: String,
    val subLeagueName: String,
    val totalRound: Int,
    val type: Int
)