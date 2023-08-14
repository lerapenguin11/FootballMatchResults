package com.example.footballmatchresults.business.models.league

data class TotalStanding(
    val color: String,
    val deduction: String,
    val deductionExplain: String,
    val drawCount: Int,
    val drawRate: String,
    val getScore: Int,
    val goalDifference: Int,
    val integral: Int,
    val loseAverage: String,
    val loseCount: Int,
    val loseRate: String,
    val loseScore: Int,
    val rank: Int,
    val recentFifthResult: Int,
    val recentFirstResult: Int,
    val recentFourthResult: Int,
    val recentSecondResult: Int,
    val recentSixthResult: Int,
    val recentThirdResult: Int,
    val red: Int,
    val teamId: String,
    val totalCount: Int,
    val winAverage: String,
    val winCount: Int,
    val winRate: String
)