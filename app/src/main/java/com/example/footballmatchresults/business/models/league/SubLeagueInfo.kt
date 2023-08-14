package com.example.footballmatchresults.business.models.league

data class SubLeagueInfo(
    val currentRound: Int,
    val hasScore: Boolean,
    val hasTwoLegs: Boolean,
    val name: String,
    val subLeagueId: String,
    val totalRound: Int
)