package com.example.footballmatchresults.business.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Points::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pointDao(): PointsDao
}