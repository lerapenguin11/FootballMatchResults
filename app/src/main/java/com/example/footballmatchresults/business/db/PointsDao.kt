package com.example.footballmatchresults.business.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PointsDao {
    @Query("SELECT * FROM notes")
    fun getAllPoints(): List<Points>

    @Insert
    fun insert(note: Points)
}