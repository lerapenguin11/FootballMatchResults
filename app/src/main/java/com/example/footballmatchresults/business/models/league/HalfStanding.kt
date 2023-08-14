package com.example.footballmatchresults.business.models.league

data class HalfStanding(
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
    val teamId: String,
    val totalCount: Int,
    val winAverage: String,
    val winCount: Int,
    val winRate: String
)