package com.example.footballmatchresults.business.models.league

data class Data(
    val awayHalfStandings: List<AwayHalfStanding>,
    val awayStandings: List<AwayStanding>,
    val conference: Boolean,
    val halfStandings: List<HalfStanding>,
    val homeHalfStandings: List<HomeHalfStanding>,
    val homeStandings: List<HomeStanding>,
    val lastUpdateTime: Int,
    val leagueColorInfos: List<LeagueColorInfo>,
    val leagueInfo: LeagueInfo,
    val subLeagueInfos: List<SubLeagueInfo>,
    val teamInfos: List<TeamInfo>,
    val totalStandings: List<TotalStanding>
)