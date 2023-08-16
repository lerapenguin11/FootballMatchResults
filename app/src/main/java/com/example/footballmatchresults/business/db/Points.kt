package com.example.footballmatchresults.business.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Points(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    
    val pointHome : String,
    val pointAway : String,
    val nameHome : String,
    val nameAway : String
)
